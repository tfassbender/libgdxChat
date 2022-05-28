package net.jfabricationgames.libgdx.chat.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import net.jfabricationgames.libgdx.chat.network.message.Message;
import net.jfabricationgames.libgdx.chat.network.message.MessageType;

public class Network {
	
	public static final int PORT = 4711;
	public static final String HOST = "localhost";
	
	public static void registerDtoClasses(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Message.class);
		kryo.register(MessageType.class);
	}
	
	private Network() {}
}
