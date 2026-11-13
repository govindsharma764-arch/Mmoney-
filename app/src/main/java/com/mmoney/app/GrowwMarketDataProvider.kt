
package com.mmoney.app

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Groww official market data provider.
 * Call these methods from a background thread, never the UI thread.
 */
class GrowwMarketDataProvider(
    private val accessToken: String
) {
    companion object {
        private const val BASE_URL = "https://api.groww.in"
        private const val API_VERSION = "1.0"
    }

    private fun get(endpoint: String, params: Map<String, String>): JSONObject {
        require(accessToken.isNotBlank()) {
            "Groww access token is missing"
        }

        val query = params.entries.joinToString("&") {
            "${encode(it.key)}=${encode(it.value)}"
        }

        val url = URL("$BASE_URL$endpoint?$query")
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "GET"
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.setRequestProperty(
                "Authorization", "Bearer $accessToken"
            )
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("X-API-VERSION", API_VERSION)

            val status = connection.responseCode
            val stream = if (status in 200..299) {
                connection.inputStream
            } else {
                connection.errorStream
            }

            val response = stream?.bufferedReader()?.use {
                it.readText()
            } ?: ""

            if (status !in 200..299) {
                throw Exception("Groww API error $status: $response")
            }

            val json = JSONObject(response)

            if (json.optString("status") == "FAILURE") {
                throw Exception(
                    json.optJSONObject("error")
                        ?.optString("message")
                        ?: "Groww request failed"
                )
            }

            return json
        } finally {
            connection.disconnect()
        }
    }

    private fun encode(value: String): String =
        URLEncoder.encode(value, "UTF-8")

    /**
     * Fetch LTP for up to 50 instruments.
     * Example: NSE_NIFTY25NOV25000CE
     */
    fun getLtp(
        segment: String,
        exchangeSymbols: List<String>
    ): JSONObject {
        require(exchangeSymbols.isNotEmpty()) {
            "At least one symbol is required"
        }
        require(exchangeSymbols.size <= 50) {
            "Maximum 50 symbols per request"
        }

        return get(
            "/v1/live-data/ltp",
            mapOf(
                "segment" to segment,
                "exchange_symbols" to exchangeSymbols.joinToString(",")
            )
        ).getJSONObject("payload")
    }

    /**
     * Fetch complete quote for one instrument.
     */
    fun getQuote(
        exchange: String,
        segment: String,
        tradingSymbol: String
    ): JSONObject {
        return get(
            "/v1/live-data/quote",
            mapOf(
                "exchange" to exchange,
                "segment" to segment,
                "trading_symbol" to tradingSymbol
            )
        ).getJSONObject("payload")
    }
}
