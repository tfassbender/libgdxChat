package net.jfabricationgames.libgdx.chat.client;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import net.jfabricationgames.libgdx.chat.Game;
import net.jfabricationgames.libgdx.desktop.log.LogConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("libGdxChat");
		config.setWindowedMode(800, 600);
		Game game = Game.createInstance(() -> configureLog());
		new Lwjgl3Application(game, config);
	}
	
	private static void configureLog() {
		new LogConfiguration().configureLog();
	}
}
