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
import com.badlogic.gdx.scenes.scene2d.ui.Value;
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
		
		Skin skin = Game.getInstance().getSkin();
		
		textFieldName = new TextField("", skin);
		TextButton buttonLogin = new TextButton("Login", skin);
		labelError = new Label("", skin);
		labelError.getStyle().fontColor = Color.RED;
		
		table.columnDefaults(0).minWidth(350);
		table.add(textFieldName);
		table.row().padTop(10);
		table.add(buttonLogin);
		table.row().padTop(20);
		table.add(labelError).width(Value.percentHeight(0.7f));
		labelError.setWrap(true);
		
		buttonLogin.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				login();
			}
		});
	}
	
	private void login() {
		Gdx.app.log(getClass().getSimpleName(), "logging in with user name: " + textFieldName.getText());
		if (textFieldName.getText().isEmpty()) {
			labelError.setText("Cannot login with empty name. Please choose a username to login.");
		}
		else {
			labelError.setText("");
			Game.getInstance().setScreen(new ChatScreen());
			dispose();
		}
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		Game.getInstance().removeInputProcessor(stage);
		stage.dispose();
	}
}
