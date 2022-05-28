package net.jfabricationgames.libgdx.chat.network.client;

import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import net.jfabricationgames.libgdx.chat.network.message.Message;
import net.jfabricationgames.libgdx.chat.network.message.MessageType;

public class ChatClientListener extends Listener {
	
	public Consumer<Message> sendMessageConsumer;
	public Consumer<Message> receiveMessageConsumer;
	public String username;
	
	public ChatClientListener(Consumer<Message> sendMessageConsumer, Consumer<Message> receiveMessageConsumer, String username) {
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
		if (object instanceof Message) {
			Gdx.app.debug(getClass().getSimpleName(), "received message from server: " + object);
			receiveMessageConsumer.accept((Message) object);
		}
	}
	
	@Override
	public void disconnected(Connection connection) {
		Gdx.app.log(getClass().getSimpleName(), "Disconnected");
	}
}
