package edu.java.bot.commands;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.MockDB;

public class StartCommand  implements CommandInterface {

    public String command() {
        return "/start";
    }

    public String description() {
        return "Registrate user";
    }

    public SendMessage handle(Update update) {
        Object chatId = update.message().chat().id();
        MockDB.createUser(chatId);
        return new SendMessage(chatId, "Hello, dear user!");
    }
}
