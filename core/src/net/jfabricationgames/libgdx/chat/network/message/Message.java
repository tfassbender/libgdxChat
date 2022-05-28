package net.jfabricationgames.libgdx.chat.network.message;

public class Message {
	
	public String user;
	public String text;
	public MessageType type;
	
	public Message() {
		this(MessageType.CHAT);
	}
	
	public Message(MessageType type) {
		this.type = type;
	}
	
	public Message setUser(String user) {
		this.user = user;
		return this;
	}
	
	public Message setText(String text) {
		this.text = text;
		return this;
	}
	
	public Message setType(MessageType type) {
		this.type = type;
		return this;
	}
	
	@Override
	public String toString() {
		return "Message [user=" + user + ", text=" + text + ", type=" + type + "]";
	}
}
