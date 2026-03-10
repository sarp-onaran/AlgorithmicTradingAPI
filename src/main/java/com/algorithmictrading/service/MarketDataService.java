package com.algorithmictrading.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;

/**
 * Service responsible for fetching real-time cryptocurrency market data.
 * Utilizes the CoinGecko API for accurate pricing information.
 */
public class MarketDataService {

    private final OkHttpClient client;

    public MarketDataService() {
        this.client = new OkHttpClient();
    }

    /**
     * Retrieves the current price of a specified cryptocurrency in USD.
     *
     * @param cryptoId The unique identifier of the cryptocurrency (e.g., "bitcoin", "ethereum").
     * @return The current price in USD, or -1.0 if the fetch operation fails.
     */
    public double getCurrentPrice(String cryptoId) {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=" + cryptoId + "&vs_currencies=usd";
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();

                // Prevent NullPointerException by checking if the cryptoId exists in the JSON
                if (jsonObject.has(cryptoId)) {
                    return jsonObject.getAsJsonObject(cryptoId).get("usd").getAsDouble();
                } else {
                    System.err.println("⚠️ Warning: Cryptocurrency '" + cryptoId + "' not found in the API response.");
                }
            } else {
                System.err.println("❌ API Request failed. HTTP Status Code: " + response.code());
            }
        } catch (IOException e) {
            System.err.println("❌ Network error occurred while fetching price: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ An unexpected error occurred: " + e.getMessage());
        }

        return -1.0; // Return indicator for failure
    }
}
