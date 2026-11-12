        
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
            setPadding(18, 26, 18, 26)
            setBackgroundColor(bg)
        }

        val scroll = ScrollView(this).apply {
            addView(page)
        }

        page.addView(text("MMONEY AI", 30, mint, true))
        page.addView(text("AI TRADE ANALYSIS • OPTIONS", 13, muted, true))
        page.addView(space(18))

        page.addView(card(
            "MARKET WATCH",
            "NIFTY 50  •  BANK NIFTY  •  SENSEX",
            "Live index prices: NOT CONNECTED"
        ))
        page.addView(space(12))

        page.addView(card(
            "AI TRADE SIGNAL",
            "NO TRADE",
            "Verified live price and option-chain data required before CALL or PUT analysis."
        ))
        page.addView(space(12))

        page.addView(card(
            "TRADE PLAN",
            "Direction: —\n" +
            "Suggested strike: —\n" +
            "Entry premium: —\n" +
            "Stop-loss: —\n" +
            "Target 1: —\n" +
            "Target 2: —\n" +
            "Target 3: —",
            "Levels will appear only after a validated market setup."
        ))
        page.addView(space(12))

        page.addView(card(
            "MARKET CONFIRMATION",
            "Price action: Waiting for data\n" +
            "Option-chain OI: Waiting for data\n" +
            "Volume: Waiting for data\n" +
            "Volatility: Waiting for data\n" +
            "Trend: Waiting for data",
            "Analysis combines price action, technical indicators, OI, volume and volatility."
        ))
        page.addView(space(12))

        page.addView(card(
            "SETUP QUALITY",
            "Not available",
            "No confidence score without verified current market data. Signals are not guaranteed."
        ))
        page.addView(space(12))

        page.addView(card(
            "GROWW CONNECTION",
            "NOT CONNECTED",
            "Manual execution for now. Official Groww API access and testing are required before automated orders."
        ))

        page.addView(space(16))

        page.addView(text(
            "DEVELOPMENT BUILD • LIVE SIGNALS NOT CONNECTED",
            11,
            muted,
            false
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
        if (bold) {
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        setPadding(0, 6, 0, 6)
    }

    private fun space(height: Int) = TextView(this).apply {
        this.height = height
    }

    private fun card(
        title: String,
        body: String,
        note: String
    ) = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(16, 14, 16, 14)
        setBackgroundColor(panel)

        addView(text(title, 16, mint, true))
        addView(text(body, 17, Color.WHITE, true))
        addView(text(note, 12, muted, false))
    }
}
