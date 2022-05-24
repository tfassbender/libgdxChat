package net.jfabricationgames.libgdx.chat.network;

import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ChatClientListener extends Listener {
	
	public Consumer<Message> sendMessageConsumer;
	public Consumer<String> receiveMessageConsumer;
	public String username;
	
	public ChatClientListener(Consumer<Message> sendMessageConsumer, Consumer<String> receiveMessageConsumer, String username) {
		this.username = username;
		this.sendMessageConsumer = sendMessageConsumer;
		this.receiveMessageConsumer = receiveMessageConsumer;
	}
	
	@Override
	public void connected(Connection connection) {
		Gdx.app.log(getClass().getSimpleName(), "Connection established - sending login message with username: " + username);
		sendMessageConsumer.accept(new Message(MessageType.LOGIN).setUser(username));
	}
	
	@Override
	public void received(Connection connection, Object object) {
		receiveMessageConsumer.accept(object.toString());
	}
	
	@Override
	public void disconnected(Connection connection) {
		Gdx.app.log(getClass().getSimpleName(), "Disconnected");
	}
}
