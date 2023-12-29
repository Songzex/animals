package com.scy.from.message;

public class ChatMessage {
    private String sender;
    private String content;
    private MessageType type;

    // getters and setters

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}

