package net.jfabricationgames.libgdx.chat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	
	private static Game instance;
	
	private Runnable preGameConfigurator;
	
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
	}
	
	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
	}
	
	@Override
	public void dispose() {}
}
