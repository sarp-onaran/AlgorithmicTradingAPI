# Algorithmic Trading API 📈🤖

A robust, enterprise-grade backend microservice built with **Java** for algorithmic cryptocurrency trading. This project simulates live market trading using real-time data and automated decision-making engines.

## 🛠️ Technology Stack & Architecture
- **Language:** Java 17
- **Database:** SQLite (Embedded DB for persistent balance tracking)
- **External API:** [CoinGecko API](https://www.coingecko.com/en/api) via OkHttp & Gson
- **Architecture:** Layered Multi-Tier Design (`Model` - `Repository` - `Service` - `Strategy`)
- **Design Patterns:** Strategy Pattern (for easily swapping out Buy/Sell algorithms)

## ✨ Core Features
- **Real-Time Market Data:** Fetches live cryptocoin prices (e.g., Bitcoin, Ethereum) directly from CoinGecko.
- **Strategy Pattern Implementation:** Trading algorithms are abstracted into custom strategies (e.g., `PriceThresholdStrategy`), making the bot incredibly modular.
- **Automated Trading Engine:** The `TradingBot` connects the user's wallet, live market data, and the selected strategy to make split-second "BUY", "SELL", or "HOLD" decisions.
- **Database Persistence:** Real-time wallet balances are persistently saved to an SQLite database (`trading_bot.db`) using UPSERT logic. User accounts survive application restarts seamlessly.

## 🚀 How to Run
1. Clone the repository.
2. Build with Maven: `mvn clean install`
3. Run the main class: `com.algorithmictrading.Main`

*(This is an educational project demonstrating object-oriented programming, design patterns, and external API integration in Java.)*
