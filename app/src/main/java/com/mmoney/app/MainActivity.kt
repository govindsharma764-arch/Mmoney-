
package com.mmoney.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.widget.*
import android.text.InputType
import java.util.Locale
import kotlin.math.floor

class MainActivity : Activity() {
    private val bg = Color.rgb(10, 16, 30)
    private val panel = Color.rgb(22, 32, 52)
    private val mint = Color.rgb(86, 230, 176)
    private val muted = Color.rgb(170, 185, 205)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 28, 20, 28)
            setBackgroundColor(bg)
        }

        val scroll = ScrollView(this)
        scroll.addView(page)

        page.addView(text("MMONEY AI", 28, mint, true))
        page.addView(text("TRADE WITH DISCIPLINE", 12, muted, false))
        page.addView(space(18))

        page.addView(card(
            "MULTI-INDEX SCANNER",
            "NIFTY 50  •  BANK NIFTY  •  SENSEX",
            "Waiting for verified live market data."
        ))

        page.addView(space(12))

        page.addView(card(
            "AI SETUP STATUS",
            "NO TRADE",
            "Live prices and option-chain data are not connected yet."
        ))

        page.addView(space(12))
        page.addView(text("POSITION SIZE CALCULATOR", 18, Color.WHITE, true))

        val capital = field("Capital (₹)", "10000")
        val entry = field("Option entry premium (₹)")
        val stop = field("Stop-loss premium (₹)")
        val lot = field("Lot size")

        page.addView(capital)
        page.addView(entry)
        page.addView(stop)
        page.addView(lot)

        val result = text(
            "Enter values to calculate quantity.",
            14, muted, false
        )
        page.addView(result)

        page.addView(Button(this).apply {
            text = "CALCULATE POSITION SIZE"
            setOnClickListener {
                val c = capital.value().toDoubleOrNull()
                val e = entry.value().toDoubleOrNull()
                val s = stop.value().toDoubleOrNull()
                val l = lot.value().toDoubleOrNull()

                if (c == null || e == null || s == null ||
                    l == null || c <= 0 || e <= s || s <= 0 || l <= 0
                ) {
                    result.text = "Please enter valid values. Entry must be above SL."
                } else {
                    val riskBudget = c * 0.02
                    val lots = floor(riskBudget / ((e - s) * l)).toInt()
                    val quantity = lots * l
                    val actualRisk = quantity * (e - s)
                    val target = e + 3 * (e - s)

                    result.text = String.format(
                        Locale.US,
                        "Risk budget (2%%): ₹%.2f\nLots: %d\nQuantity: %.0f\nEstimated risk: ₹%.2f\n3R target premium: ₹%.2f",
                        riskBudget, lots, quantity, actualRisk, target
                    )

                    if (lots == 0) {
                        result.append("\nRisk budget is insufficient for one lot.")
                    }
                }
            }
        })

        page.addView(space(14))

        page.addView(card(
            "TRADING DISCIPLINE",
            "• Decide SL before entry\n• Avoid revenge trading\n• Never widen SL to avoid a loss",
            "Calculator uses an illustrative 2% risk setting."
        ))

        page.addView(space(12))

        page.addView(card(
            "GROWW CONNECTION",
            "NOT CONNECTED",
            "Official API access and verified market data are still required. Live orders are disabled."
        ))

        page.addView(space(16))
        page.addView(text(
            "DEVELOPMENT BUILD • NOT A LIVE SIGNAL SERVICE",
            11, muted, false
        ))

        setContentView(scroll)
    }

    private fun text(
        value: String,
        size: Int,
        color: Int,
        bold: Boolean
    ) = TextView(this).apply {
        text = value
        textSize = size.toFloat()
        setTextColor(color)
        if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
        setPadding(0, 6, 0, 6)
    }

    private fun space(height: Int) = TextView(this).apply {
        this.height = height
    }

    private fun card(title: String, body: String, note: String) =
        LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 14, 16, 14)
            setBackgroundColor(panel)
            addView(text(title, 16, mint, true))
            addView(text(body, 15, Color.WHITE, true))
            addView(text(note, 12, muted, false))
        }

    private fun field(label: String, initial: String = "") =
        EditText(this).apply {
            hint = label
            setSingleLine(true)
            inputType = InputType.TYPE_CLASS_NUMBER or
                InputType.TYPE_NUMBER_FLAG_DECIMAL
            setTextColor(Color.WHITE)
            setHintTextColor(muted)
            if (initial.isNotEmpty()) setText(initial)
            setPadding(12, 8, 12, 8)
            setBackgroundColor(panel)
        }

    private fun EditText.value() =
        text?.toString()?.trim().orEmpty()
}
