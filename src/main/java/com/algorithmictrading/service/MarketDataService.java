package com.algorithmictrading.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;

public class MarketDataService {

    private final OkHttpClient client;

    public MarketDataService() {
        this.client = new OkHttpClient();
    }

    // Fetches real-time price from CoinGecko API
    public double getCurrentPrice(String cryptoId) {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=" + cryptoId + "&vs_currencies=usd";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();

                // Parse the incoming JSON data
                JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
                return jsonObject.getAsJsonObject(cryptoId).get("usd").getAsDouble();
            }
        } catch (IOException e) {
            System.err.println("Error occurred while fetching price: " + e.getMessage());
        }
        return -1.0; // Returns -1 in case of error
    }
}
