package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.UserMessageProcessor;

public class HelpCommand implements CommandInterface {

    public String command() {
        return "/help";
    }

    public String description() {
        return "Print all available options";
    }

    public SendMessage handle(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        for(var command : UserMessageProcessor.commands()) {
            stringBuilder.append(command.command() + " " + command.description() + "\n");
        }

        return new SendMessage(update.message().chat().id(), stringBuilder.toString());
    }

}
