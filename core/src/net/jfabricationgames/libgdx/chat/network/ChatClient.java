package net.jfabricationgames.libgdx.chat.network;

import java.io.IOException;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.esotericsoftware.kryonet.Client;

public class ChatClient implements Disposable {
	
	private static final int PORT = 4711;
	private static final String HOST = "localhost";
	
	private Client client;
	private String username;
	
	private Json json;
	
	private Consumer<Message> messageReceiver;
	
	public ChatClient(String username, Consumer<Message> messageReveiver) {
		this.username = username;
		this.messageReceiver = messageReveiver;
		
		json = new Json();
		
		client = new Client();
		client.start();
		
		client.addListener(new ChatClientListener(this::sendMessage, this::receiveMessage, username));
		
		new Thread(this::connect, "connect").start();
	}
	
	private void connect() {
		try {
			client.connect(5000, HOST, PORT);
		}
		catch (IOException e) {
			Gdx.app.error(getClass().getSimpleName(), "Connection cound not be established", e);
		}
	}
	
	public void sendChatMessage(String message) {
		sendMessage(new Message().setUser(username).setText(message));
	}
	
	private void sendMessage(Message message) {
		String messageString = json.toJson(message, Message.class);
		Gdx.app.log(getClass().getSimpleName(), "Sending message to server: " + messageString);
		client.sendTCP(messageString);
	}
	
	private void receiveMessage(String message) {
		Message deserialized = json.fromJson(Message.class, message);
		messageReceiver.accept(deserialized);
	}
	
	@Override
	public void dispose() {
		sendMessage(new Message(MessageType.LOGOUT).setUser(username));
		client.stop();
	}
}
