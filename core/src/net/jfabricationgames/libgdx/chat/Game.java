package net.jfabricationgames.libgdx.chat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import net.jfabricationgames.libgdx.chat.screen.LoginScreen;

public class Game extends com.badlogic.gdx.Game {
	
	private static Game instance;
	
	private Runnable preGameConfigurator;
	private InputMultiplexer inputMultiplexer;
	
	public static synchronized Game createInstance(Runnable preGameConfigurator) {
		if (instance == null) {
			instance = new Game(preGameConfigurator);
		}
		return instance;
	}
	
	public static Game getInstance() {
		return instance;
	}
	
	private Game(Runnable preGameConfigurator) {
		this.preGameConfigurator = preGameConfigurator;
	}
	
	@Override
	public void create() {
		preGameConfigurator.run();
		
		inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		// do not call this from the constructor, or it will cause an UnsatisfiedLinkError when creating a SpriteBatch or a Stage in LoginScreen
		setScreen(new LoginScreen());
	}
	
	public void addInputProcessor(InputProcessor processor) {
		inputMultiplexer.addProcessor(processor);
	}
	public void removeInputProcessor(InputProcessor processor) {
		inputMultiplexer.removeProcessor(processor);
	}
}
