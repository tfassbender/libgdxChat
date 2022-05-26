package net.jfabricationgames.libgdx.chat.network.server;

import java.io.IOException;

public class ChatServerMain {
	
	public static void main(String[] args) throws IOException {
		ChatServer server = new ChatServer();
		server.start();
	}
}
