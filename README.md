# Telegram Bot Example in Java

A simple "Hello World" Telegram bot implementation in Java using the TelegramBots library. This bot demonstrates basic functionality including command handling, message echoing, and time display.

## Features

- **Command Support**: Responds to `/start`, `/help`, and `/time` commands
- **Message Echoing**: Echoes back user messages with personalized greetings
- **Logging**: Comprehensive logging using SLF4J
- **Maven Build**: Complete Maven project structure with dependencies

## Bot Commands

- `/start` - Shows welcome message and bot introduction
- `/help` - Displays available commands and usage instructions
- `/time` - Returns current date and time
- Any text message - Bot responds with a personalized greeting

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- A Telegram bot token (obtained from [@BotFather](https://t.me/botfather))

## Setup Instructions

### 1. Create a Telegram Bot

1. Open Telegram and search for [@BotFather](https://t.me/botfather)
2. Start a conversation and send `/newbot`
3. Follow the instructions to create your bot
4. Save the bot token provided by BotFather
5. Note your bot's username

### 2. Configure the Bot

Set your bot credentials using one of these methods:

#### Option A: Environment Variables (Recommended)
```bash
export BOT_TOKEN="your_bot_token_here"
export BOT_USERNAME="your_bot_username"
```

#### Option B: System Properties
```bash
java -DBOT_TOKEN="your_bot_token_here" -DBOT_USERNAME="your_bot_username" -jar target/telegram-bot-example-1.0.0.jar
```

#### Option C: Modify Source Code
Edit `src/main/java/com/example/telegram/HelloWorldBot.java` and replace:
```java
private static final String BOT_TOKEN = "YOUR_BOT_TOKEN_HERE";
private static final String BOT_USERNAME = "YourBotUsername";
```

### 3. Build and Run

```bash
# Clone the repository
git clone https://github.com/Lost-Fly/HelloWorldCoop.git
cd HelloWorldCoop

# Compile the project
mvn clean compile

# Package the application
mvn package

# Run the bot (make sure BOT_TOKEN and BOT_USERNAME are set)
java -jar target/telegram-bot-example-1.0.0.jar
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/telegram/
│   │   ├── TelegramBotApplication.java  # Main application entry point
│   │   └── HelloWorldBot.java           # Bot implementation
│   └── resources/
│       └── application.properties       # Configuration file
├── pom.xml                              # Maven configuration
└── README.md                            # This file
```

## Dependencies

- **TelegramBots API**: `org.telegram:telegrambots:6.8.0`
- **SLF4J Simple Logger**: `org.slf4j:slf4j-simple:2.0.9`

## Usage Example

Once the bot is running:

1. Find your bot on Telegram by searching for its username
2. Start a conversation with `/start`
3. Try different commands like `/help` or `/time`
4. Send any text message to see the echo functionality

## Development

### Building
```bash
mvn clean compile
```

### Packaging
```bash
mvn package
```

### Running in Development
```bash
mvn exec:java -Dexec.mainClass="com.example.telegram.TelegramBotApplication"
```

## Security Notes

- Never commit your bot token to version control
- Use environment variables or external configuration for sensitive data
- Consider implementing rate limiting for production use
- Review Telegram's Bot API terms of service

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is provided as an educational example. Use it as a starting point for your own Telegram bot projects.

## Troubleshooting

### Common Issues

1. **"Unauthorized" Error**: Check that your bot token is correct
2. **Bot Not Responding**: Ensure the bot is running and the token/username are properly set
3. **Compilation Errors**: Verify you have Java 11+ and Maven 3.6+

### Getting Help

- Check the [TelegramBots Java Library Documentation](https://github.com/rubenlagus/TelegramBots)
- Review Telegram's [Bot API Documentation](https://core.telegram.org/bots/api)
- Look at the application logs for error messages
