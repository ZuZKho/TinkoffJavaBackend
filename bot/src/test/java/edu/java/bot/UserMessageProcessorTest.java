package edu.java.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMessageProcessorTest {

    @Test
    void test() {
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(message.chat().id()).thenReturn((long) 1);
        Mockito.when(message.text()).thenReturn("/start");

        SendMessage sendMessage = UserMessageProcessor.process(update);
        SendMessage expected = new SendMessage((long) 1, "Hello, dear user!");
        assertEquals(expected.getParameters(), sendMessage.getParameters());
    }

}
