package net.jfabricationgames.libgdx.chat.network.client;

import java.io.IOException;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Client;

import net.jfabricationgames.libgdx.chat.network.Network;
import net.jfabricationgames.libgdx.chat.network.message.Message;

public class ChatClient implements Disposable {
	
	private Client client;
	private String username;
	
	private Consumer<Message> messageReceiver;
	
	public ChatClient(String username, Consumer<Message> messageReveiver) {
		this.username = username;
		this.messageReceiver = messageReveiver;
		
		client = new Client();
		client.start();
		
		Network.registerDtoClasses(client);
		
		client.addListener(new ChatClientListener(this::sendMessage, this::receiveMessage, username));
		
		new Thread(this::connect, "connect").start();
	}
	
	private void connect() {
		try {
			client.connect(5000, Network.HOST, Network.PORT);
		}
		catch (IOException e) {
			Gdx.app.error(getClass().getSimpleName(), "Connection cound not be established", e);
		}
	}
	
	public void sendChatMessage(String message) {
		sendMessage(new Message().setUser(username).setText(message));
	}
	
	private void sendMessage(Message message) {
		Gdx.app.log(getClass().getSimpleName(), "Sending message to server: " + message);
		client.sendTCP(message);
	}
	
	private void receiveMessage(Message message) {
		messageReceiver.accept(message);
	}
	
	@Override
	public void dispose() {
		client.stop();
	}
}
