
package com.mmoney.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.widget.*

class MainActivity : Activity() {

    private val bg = Color.rgb(10, 16, 30)
    private val panel = Color.rgb(22, 32, 52)
    private val mint = Color.rgb(86, 230, 176)
    private val muted = Color.rgb(170, 185, 205)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 30, 20, 25)
            setBackgroundColor(bg)
        }

        val scroll = ScrollView(this)
        scroll.addView(page)

        page.addView(label("MMONEY AI", 32, mint, true))
        page.addView(label(
            "YOUR AI NIFTY TRADING ASSISTANT",
            12, muted, true
        ))
        page.addView(gap(20))

        val signal = card()
        signal.addView(label("AI TRADE SIGNAL", 14, mint, true))
        signal.addView(label("NO TRADE", 30, Color.WHITE, true))
        signal.addView(label(
            "Waiting for verified live market data...",
            13, muted, false
        ))
        page.addView(signal)
        page.addView(gap(14))

        val plan = card()
        plan.addView(label("TRADE ANALYSIS", 14, mint, true))
        plan.addView(label(
            "Market: NIFTY 50 OPTIONS\n" +
            "Direction: —\n" +
            "Strike Price: —\n" +
            "Entry Price: —\n" +
            "Stop Loss: —\n" +
            "Target Price: —",
            16, Color.WHITE, true
        ))

        plan.addView(gap(10))
        plan.addView(label("POSITION SIZE", 14, mint, true))
        plan.addView(label(
            "Capital: ₹10,000\n" +
            "Risk Per Trade: 5% (₹500)\n" +
            "Quantity: —\n" +
            "Lots: —\n" +
            "Estimated Loss: —\n" +
            "Potential Profit (1:3): —",
            16, Color.WHITE, true
        ))
        plan.addView(label(
            "Quantity will be calculated using live entry, stop-loss and current exchange lot size.",
            12, muted, false
        ))
        page.addView(plan)
        page.addView(gap(14))

        val profit = card()
        profit.addView(label("SMART PROFIT BOOKING", 14, mint, true))
        profit.addView(label(
            "Target 1: Partial profit booking\n" +
            "Remaining Quantity: Hold\n" +
            "Trailing Stop Loss: Active after setup\n" +
            "Target 2: Next profit level\n" +
            "Target 3: Final target",
            15, Color.WHITE, true
        ))
        profit.addView(label(
            "Trailing SL and partial exits require verified live prices and broker order support.",
            12, muted, false
        ))
        page.addView(profit)
        page.addView(gap(14))

        val approval = card()
        approval.addView(label("YOUR APPROVAL", 14, mint, true))
        approval.addView(label(
            "Real order sirf tumhare YES ke baad place hoga.",
            13, muted, false
        ))

        val yes = Button(this).apply {
            text = "YES - PLACE ORDER"
            isEnabled = false
        }

        val no = Button(this).apply {
            text = "NO - SKIP TRADE"
            isEnabled = false
        }

        approval.addView(yes)
        approval.addView(no)
        page.addView(approval)
        page.addView(gap(14))

        val broker = card()
        broker.addView(label("GROWW BROKER", 14, mint, true))
        broker.addView(label("NOT CONNECTED", 20, Color.WHITE, true))
        broker.addView(label(
            "Live API connection required for market data and orders.",
            12, muted, false
        ))
        page.addView(broker)

        page.addView(gap(18))
        page.addView(label(
            "AI ANALYSIS • MANUAL APPROVAL • 5% RISK",
            11, muted, true
        ))

        setContentView(scroll)
    }

    private fun card() = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(18, 16, 18, 16)
        setBackgroundColor(panel)
    }

    private fun label(
        value: String,
        size: Int,
        color: Int,
        bold: Boolean
    ) = TextView(this).apply {
        text = value
        textSize = size.toFloat()
        setTextColor(color)
        if (bold) {
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        setPadding(0, 6, 0, 6)
    }

    private fun gap(height: Int) = TextView(this).apply {
        this.height = height
    }
}

