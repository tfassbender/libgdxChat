package net.jfabricationgames.libgdx.chat.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	
	private TextField textFieldName;
	private Label labelError;
	
	public LoginScreen() {
		stage = new Stage(new ScreenViewport());
		Game.getInstance().addInputProcessor(stage);
	}
	
	@Override
	public void show() {
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		stage.addActor(table);
		
		Skin skin = new Skin(Gdx.files.internal("skin/rainbow-ui.json"));
		
		textFieldName = new TextField("", skin);
		TextButton buttonLogin = new TextButton("Login", skin);
		labelError = new Label("", skin);
		labelError.getStyle().fontColor = Color.RED;
		
		table.add().fillX();
		table.add(textFieldName).fillX().uniformX();
		table.add().fillX();
		table.row().pad(10, 0, 0, 0);
		table.add().fillX();
		table.add(buttonLogin).fillX().uniformX();
		table.add().fillX();
		table.row().pad(20, 0, 0, 0);
		table.add(labelError).colspan(3).width(500);
		labelError.setWrap(true);
		
		buttonLogin.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				login();
			}
		});
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		stage.act(delta);
		stage.draw();
	}
	
	private void login() {
		Gdx.app.log(getClass().getSimpleName(), "logging in with user name: " + textFieldName.getText());
		if (textFieldName.getText().isEmpty()) {
			labelError.setText("Cannot login with empty name. Please choose a username to login.");
		}
		else {
			labelError.setText("");
			//TODO change to chat screen
		}
	}
}
