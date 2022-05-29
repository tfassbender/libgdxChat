package net.jfabricationgames.libgdx.chat.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jfabricationgames.libgdx.chat.network.server.ChatServer;

public class ChatServerMain {
	
	private static final Logger log = LoggerFactory.getLogger(ChatServerMain.class);
	
	public static void main(String[] args) throws IOException {
		ChatServer server = new ChatServer();
		server.start();
		
		Thread.setDefaultUncaughtExceptionHandler(new ServerGlobalExceptionHandler());
	}
	
	public static class ServerGlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
		
		@Override
		public void uncaughtException(Thread thread, Throwable throwable) {
			log.error("SERVER_UNCAUGHT_EXCEPTION", "Uncaught exception in thread [{}]: {}", thread.getName(), throwable);
		}
	}
}
