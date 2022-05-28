package net.jfabricationgames.libgdx.chat.network.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badlogic.gdx.utils.Json;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import net.jfabricationgames.libgdx.chat.network.Network;
import net.jfabricationgames.libgdx.chat.network.message.Message;
import net.jfabricationgames.libgdx.chat.network.message.MessageType;

public class ChatServer {
	
	private static final Logger log = LoggerFactory.getLogger(ChatServer.class);
	
	private Server server;
	
	private Json json;
	
	public ChatServer() {
		server = new Server() {
			@Override
			protected Connection newConnection() {
				return new ChatConnection();
			}
		};
		
		server.addListener(new Listener() {
			@Override
			public void received(Connection connection, Object object) {
				if (object instanceof String) {
					// all connections are ChatConnections, because the method in the server is overwritten
					handleMessage((ChatConnection) connection, (String) object);
				}
			}
			
			@Override
			public void disconnected(Connection connection) {
				ChatConnection chatConnection = (ChatConnection) connection;
				sendMessageToAllExcept(chatConnection);
			}
		});
		
		json = new Json();
	}
	
	public void start() throws IOException {
		log.info("Starting server on port: {}", Network.PORT);
		server.bind(Network.PORT);
		server.start();
		log.info("Server successfully started on port: {}", Network.PORT);
	}
	
	private void handleMessage(ChatConnection connection, String messageString) {
		Message message = json.fromJson(Message.class, messageString);
		
		if (message == null) {
			log.error("The message string could not be parsed to Message.class: {}", messageString);
		}
		else {
			log.debug("Received message from {}: type: {} - text: {}", message.user, message.type, message.text);
			
			if (message.type == MessageType.LOGIN) {
				String name = message.user;
				if (name != null) {
					connection.name = name;
				}
			}
			
			//pass on the message to all clients
			server.sendToAllExceptTCP(connection.getID(), messageString);
		}
	}
	
	private void sendMessageToAllExcept(ChatConnection chatConnection) {
		Message message = new Message(MessageType.LOGOUT).setUser(chatConnection.name);
		String messageString = json.toJson(message);
		server.sendToAllExceptTCP(chatConnection.getID(), messageString);
	}
	
	private class ChatConnection extends Connection {
		
		public String name;
	}
}
