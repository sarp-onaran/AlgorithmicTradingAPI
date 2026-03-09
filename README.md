# 📈 Algorithmic Trading & Portfolio Management API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

A robust, backend-focused Java application designed to simulate algorithmic trading. It fetches real-time cryptocurrency market data and executes automated trades based on predefined, customizable strategies using Object-Oriented Programming principles.

## 🚀 Key Features
- **Real-Time Data Integration:** Connects to the CoinGecko API via `OkHttp` to fetch live market prices.
- **Strategy Design Pattern:** Employs a flexible architecture where trading algorithms (e.g., Price Thresholds) can be swapped or added without modifying core logic.
- **Persistent Data Storage:** Uses `SQLite` to manage user portfolios, balances, and track state across sessions.
- **Audit Logging:** Automatically records all executed trades (BUY/SELL) into a local text file for transaction history analysis.

## 🏗️ Architecture & Tech Stack
- **Language:** Java (JDK 17+)
- **Build Tool:** Maven
- **Database:** SQLite (JDBC)
- **HTTP Client:** OkHttp3
- **JSON Parsing:** Google Gson
- **Design Patterns:** Strategy Pattern, Singleton (Database Connection)

## ⚙️ Getting Started

### Prerequisites
Make sure you have Java and Maven installed on your system.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/sarp-onaran/AlgorithmicTradingAPI.git
   ```

2. Navigate to the project directory:
   ```bash
   cd AlgorithmicTradingAPI
   ```

3. Build the project and install dependencies:
   ```bash
   mvn clean install
   ```

4. Run the main application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.algorithmictrading.Main"
   ```

## 🧠 How The Strategy Works
The bot currently uses a `PriceThresholdStrategy`.

- If the current price drops below the defined **Buy Threshold**, the bot executes a **BUY** order.
- If the price rises above the **Sell Threshold**, the bot executes a **SELL** order.
- Otherwise, it **holds** the position.

> Future plans include adding moving averages and RSI-based trading algorithms.
