package com.example.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application class for the Telegram Bot example.
 * This class initializes and starts the Telegram bot.
 */
public class TelegramBotApplication {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Telegram Bot Application...");

        try {
            // Create the TelegramBotsApi object to register our bot
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            
            // Register our bot
            HelloWorldBot bot = new HelloWorldBot();
            botsApi.registerBot(bot);
            
            logger.info("Telegram bot started successfully!");
            logger.info("Bot username: @{}", bot.getBotUsername());
            logger.info("Bot is now running. Press Ctrl+C to stop.");
            
        } catch (TelegramApiException e) {
            logger.error("Error starting telegram bot: {}", e.getMessage(), e);
            System.exit(1);
        }
    }
}