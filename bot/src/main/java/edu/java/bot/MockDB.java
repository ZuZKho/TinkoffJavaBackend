package edu.java.bot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MockDB {

    private static final HashMap<Object, User> users = new HashMap<>();

    public static void createUser(Object chatId) {
        if (!users.containsKey(chatId)) {
            users.put(chatId, new User(chatId, new HashSet<String>()));
        }
    }

    public static void addLink(Object chatId, String link) {
        if (!users.containsKey(chatId)) {
            createUser(chatId);
        }
        users.get(chatId).links.add(link);
    }

    public static int deleteLink(Object chatId, String link) {
        if (!users.get(chatId).links.contains(link)) {
            return 1;
        }

        users.get(chatId).links.remove(link);
        return 0;
    }

    public static List<String> getLinks(Object chatId) {
        if (!users.containsKey(chatId)) {
            return null;
        }

        return users.get(chatId).links.stream().toList();
    }

}

class User {

    User(Object chatId, HashSet<String> links) {
        this.chatId = chatId;
        this.links = links;
    }

    Object chatId;
    HashSet<String> links;
}
