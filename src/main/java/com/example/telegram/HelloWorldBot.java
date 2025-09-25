package com.example.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple "Hello World" Telegram Bot that demonstrates basic bot functionality.
 * 
 * Features:
 * - Responds to /start command with a welcome message
 * - Responds to /help command with available commands
 * - Responds to /time command with current time
 * - Echoes back any text message with a greeting
 */
public class HelloWorldBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldBot.class);

    // Bot configuration - In a real application, these should be loaded from environment variables or config file
    private static final String BOT_TOKEN = System.getProperty("BOT_TOKEN", "YOUR_BOT_TOKEN_HERE");
    private static final String BOT_USERNAME = System.getProperty("BOT_USERNAME", "YourBotUsername");

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Check if the update contains a message
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String firstName = update.getMessage().getFrom().getFirstName();
            
            logger.info("Received message from {}: {}", firstName, messageText);

            String responseText = processMessage(messageText, firstName);
            sendMessage(chatId, responseText);
        }
    }

    /**
     * Process incoming message and generate appropriate response
     */
    private String processMessage(String messageText, String firstName) {
        switch (messageText.toLowerCase()) {
            case "/start":
                return String.format("Hello %s! 👋\n\n" +
                    "Welcome to the Hello World Telegram Bot!\n" +
                    "This is a simple example bot written in Java.\n\n" +
                    "Try sending me:\n" +
                    "• /help - to see available commands\n" +
                    "• /time - to get current time\n" +
                    "• Any text - and I'll echo it back with a greeting!", firstName);

            case "/help":
                return "📋 Available Commands:\n\n" +
                    "/start - Show welcome message\n" +
                    "/help - Show this help message\n" +
                    "/time - Get current date and time\n\n" +
                    "You can also send me any text message and I'll respond with a personalized greeting!";

            case "/time":
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return String.format("🕐 Current time: %s", now.format(formatter));

            default:
                return String.format("Hello %s! 😊\n\n" +
                    "You said: \"%s\"\n\n" +
                    "Thanks for testing the Hello World bot! " +
                    "Try /help to see what else I can do.", firstName, messageText);
        }
    }

    /**
     * Send a text message to the specified chat
     */
    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
            logger.info("Message sent successfully to chat {}", chatId);
        } catch (TelegramApiException e) {
            logger.error("Failed to send message to chat {}: {}", chatId, e.getMessage(), e);
        }
    }
}