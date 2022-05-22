package net.jfabricationgames.libgdx.chat.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.jfabricationgames.libgdx.chat.Game;

public class ChatScreen extends ScreenAdapter {
	
	private Stage stage;
	
	private TextArea textAreaMessage;
	private Label labelChat;
	private List<String> listUsers;
	
	public ChatScreen() {
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
		
		labelChat = new Label("", skin);
		labelChat.setWrap(true);
		ScrollPane scrollPaneChat = new ScrollPane(labelChat, skin);
		
		listUsers = new List<>(skin);
		ScrollPane scrollPaneUsers = new ScrollPane(listUsers, skin);
		
		textAreaMessage = new TextArea("", skin);
		textAreaMessage.setPrefRows(3);
		textAreaMessage.addListener(new InputListener() {
			
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				boolean shiftKeyPressed = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);
				boolean enterKeyPressed = event.getKeyCode() == Keys.ENTER;
				if (enterKeyPressed && !shiftKeyPressed) {
					sendMessage();
					return true;
				}
				
				return false;
			}
		});
		
		TextButton buttonSend = new TextButton("Send", skin);
		buttonSend.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				sendMessage();
			}
		});
		
		table.row().height(500);
		table.add(scrollPaneChat).width(400);
		table.add(scrollPaneUsers).width(250);
		table.row();
		table.add(textAreaMessage);
		table.add(buttonSend);
	}
	
	private void sendMessage() {
		String message = textAreaMessage.getText();
		if (!message.isEmpty()) {
			//TODO send the message
			
			textAreaMessage.setText("");
		}
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		Game.getInstance().removeInputProcessor(stage);
		stage.dispose();
	}
}
