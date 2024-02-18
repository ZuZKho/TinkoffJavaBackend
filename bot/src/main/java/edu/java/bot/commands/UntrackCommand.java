package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.MockDB;

public class UntrackCommand implements CommandInterface {

    public String command() {
        return "/untrack";
    }

    public String description() {
        return "Untrack link";
    }

    public SendMessage handle(Update update) {
        var chatId = update.message().chat().id();
        String link = update.message().text().substring(8).trim();

        if (link.equals("") || link.contains(" ")) {
            return new SendMessage(chatId, "Wrong arguments, send one link as argument");
        }

        if (MockDB.deleteLink(chatId, link) != 0) {
            return new SendMessage(chatId, "No such link");
        }
        return new SendMessage(chatId, "Link succsefully deleted");
    }

}
