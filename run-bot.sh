#!/bin/bash

# Telegram Bot Runner Script
# Usage: ./run-bot.sh [bot-token] [bot-username]

set -e

echo "🤖 Telegram Bot Runner"
echo "======================"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 11 or higher."
    exit 1
fi

# Check Java version
java_version=$(java -version 2>&1 | head -n1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$java_version" -lt 11 ]; then
    echo "❌ Java 11 or higher is required. Current version: $java_version"
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven 3.6 or higher."
    exit 1
fi

# Check if bot token and username are provided
if [ -z "$BOT_TOKEN" ] && [ -z "$1" ]; then
    echo "❌ Bot token is required!"
    echo "Usage: $0 [bot-token] [bot-username]"
    echo "Or set environment variables BOT_TOKEN and BOT_USERNAME"
    exit 1
fi

if [ -z "$BOT_USERNAME" ] && [ -z "$2" ]; then
    echo "❌ Bot username is required!"
    echo "Usage: $0 [bot-token] [bot-username]"
    echo "Or set environment variables BOT_TOKEN and BOT_USERNAME"
    exit 1
fi

# Set bot credentials from arguments if provided
if [ -n "$1" ]; then
    export BOT_TOKEN="$1"
fi
if [ -n "$2" ]; then
    export BOT_USERNAME="$2"
fi

echo "🔧 Building the project..."
mvn clean package -q

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo "🚀 Starting Telegram bot..."
    echo "Bot Username: @$BOT_USERNAME"
    echo "Press Ctrl+C to stop the bot"
    echo ""
    
    java -jar target/telegram-bot-example-1.0.0.jar
else
    echo "❌ Build failed!"
    exit 1
fi