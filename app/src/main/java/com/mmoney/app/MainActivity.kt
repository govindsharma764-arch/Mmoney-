package com.mmoney.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val bg = Color.rgb(10, 16, 30)
    private val panel = Color.rgb(22, 32, 52)
    private val mint = Color.rgb(86, 230, 176)
    private val muted = Color.rgb(170, 185, 205)

    private val capitalAvailable = 20000.0
    private val riskPercent = 3.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        render()
    }

    private fun render() {

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 28, 20, 28)
            setBackgroundColor(bg)
        }

        val scroll = ScrollView(this)
        scroll.addView(page)

        page.addView(label("MMONEY AI", 30, mint, true))
        page.addView(label("NIFTY 50 OPTIONS • AI ANALYSIS", 13, muted, true))
        page.addView(gap(16))

        val capital = card()
        capital.addView(label("YOUR CAPITAL", 14, mint, true))
        capital.addView(label("₹20,000", 28, Color.WHITE, true))
        capital.addView(label("Risk per trade: 3% = ₹600", 15, muted, false))
        capital.addView(label("No daily loss limit • Per-trade risk control", 12, muted, false))
        page.addView(capital)
        page.addView(gap(12))

        val signal = card()
        signal.addView(label("AI TRADE ANALYSIS", 14, mint, true))
        signal.addView(label("NO TRADE", 25, Color.WHITE, true))
        signal.addView(label(
            "Waiting for verified live market data. CALL and PUT both will be scanned. Only the strongest confirmed setup will be shown.",
            14, muted, false
        ))
        page.addView(signal)
        page.addView(gap(12))

        val sizing = card()
        sizing.addView(label("AUTOMATIC POSITION SIZING", 14, mint, true))
        sizing.addView(label("Entry price: Waiting for live data", 14, Color.WHITE, false))
        sizing.addView(label("Stop loss: Waiting for live data", 14, Color.WHITE, false))
        sizing.addView(label("Target: Dynamic 1:3 risk-reward", 14, Color.WHITE, false))
        sizing.addView(label("Quantity and lot size: Automatic", 14, Color.WHITE, false))
        sizing.addView(label("Capital usage: —", 14, Color.WHITE, true))
        sizing.addView(label("Estimated risk: Maximum ₹600 per trade", 14, Color.WHITE, true))
        page.addView(sizing)
        page.addView(gap(12))

        val exits = card()
        exits.addView(label("SMART PROFIT MANAGEMENT", 14, mint, true))
        exits.addView(label("TP1: Partial profit booking", 14, Color.WHITE, false))
        exits.addView(label("Remaining quantity: Trailing stop-loss", 14, Color.WHITE, false))
        exits.addView(label("TP2 / TP3: Further targets", 14, Color.WHITE, false))
        exits.addView(label("Automatic exit requires verified broker API integration.", 12, muted, false))
        page.addView(exits)
        page.addView(gap(12))

        val broker = card()
        broker.addView(label("GROWW CONNECTION", 14, mint, true))
        broker.addView(label("NOT CONNECTED", 20, Color.WHITE, true))
        broker.addView(label(
            "Official Groww API integration is pending. Never share API secrets in chat.",
            13, muted, false
        ))
        page.addView(broker)
        page.addView(gap(12))

        val orderCard = card()
        orderCard.addView(label("ORDER EXECUTION", 14, mint, true))

        val placeOrder = Button(this).apply {
            text = "PLACE ORDER"
            textSize = 17f
            isEnabled = false
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(65, 75, 90))
        }

        orderCard.addView(placeOrder)
        orderCard.addView(label(
            "Waiting for Groww API connection and verified AI signal. Order requires your approval.",
            12, muted, false
        ))

        page.addView(orderCard)
        page.addView(gap(14))

        page.addView(label(
            "Safety: Options trading involves risk. Signals are not guaranteed. No trade will be recommended without verified market data.",
            12, muted, false
        ))

        setContentView(scroll)
    }

    private fun card() = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(17, 15, 17, 15)
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
        if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
        setPadding(0, 5, 0, 5)
    }

    private fun gap(height: Int) = TextView(this).apply {
        this.height = height
    }
}
