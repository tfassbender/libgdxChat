package net.jfabricationgames.libgdx.chat.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.jfabricationgames.libgdx.chat.Game;

public class LoginScreen extends ScreenAdapter {
	
	private Stage stage;
	
	public LoginScreen() {
		stage = new Stage(new ScreenViewport());
		Game.getInstance().addInputProcessor(stage);
	}
	
	@Override
	public void show() {
		Table table = new Table();
		table.setFillParent(true);
		//table.setDebug(true);
		stage.addActor(table);
		
		Skin skin = new Skin(Gdx.files.internal("skin/rainbow-ui.json"));
		
		TextField textFieldName = new TextField("", skin);
		TextButton buttonLogin = new TextButton("Login", skin);
		
		table.add(textFieldName).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(buttonLogin).fillX().uniformX();
		
		buttonLogin.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.log(getClass().getSimpleName(), "logging in with user name: " + textFieldName.getText());
			}
		});
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		stage.act(delta);
		stage.draw();
	}
}
