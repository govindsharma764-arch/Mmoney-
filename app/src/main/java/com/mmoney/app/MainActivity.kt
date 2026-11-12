   
package com.mmoney.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
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
        page.addView(label("YOUR AI NIFTY TRADING ASSISTANT", 12, muted, true))
        page.addView(gap(25))

        val signal = card()
        signal.addView(label("AI TRADE SIGNAL", 14, mint, true))
        signal.addView(label("NO TRADE", 30, Color.WHITE, true))
        signal.addView(label(
            "Waiting for verified live market data...",
            13, muted, false
        ))
        page.addView(signal)
        page.addView(gap(15))

        val plan = card()
        plan.addView(label("TRADE ANALYSIS", 14, mint, true))
        plan.addView(label(
            "Market: NIFTY 50 OPTIONS\n\n" +
            "Direction: —\n" +
            "Strike Price: —\n" +
            "Entry Price: —\n" +
            "Stop Loss: —\n" +
            "Target Price: —",
            17, Color.WHITE, true
        ))
        page.addView(plan)
        page.addView(gap(15))

        val approval = card()
        approval.addView(label("YOUR APPROVAL", 14, mint, true))
        approval.addView(label(
            "AI signal milne ke baad order tumhari permission se hi jayega.",
            13, muted, false
        ))

        val yes = Button(this).apply {
            text = "YES - PLACE ORDER"
            isEnabled = false
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Groww API not connected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val no = Button(this).apply {
            text = "NO - SKIP TRADE"
            isEnabled = false
        }

        approval.addView(yes)
        approval.addView(no)
        page.addView(approval)
        page.addView(gap(15))

        val broker = card()
        broker.addView(label("GROWW BROKER", 14, mint, true))
        broker.addView(label("NOT CONNECTED", 20, Color.WHITE, true))
        broker.addView(label(
            "Official Groww API connection required for live orders.",
            12, muted, false
        ))
        page.addView(broker)

        page.addView(gap(20))
        page.addView(label(
            "AI ANALYSIS • MANUAL APPROVAL • RISK CONTROL",
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
        setPadding(0, 7, 0, 7)
    }

    private fun gap(height: Int) = TextView(this).apply {
        this.height = height
    }
}

