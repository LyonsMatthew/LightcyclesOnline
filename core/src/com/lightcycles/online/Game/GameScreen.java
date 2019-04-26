package com.lightcycles.online.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lightcycles.online.Settings;

import java.util.ArrayList;

public class GameScreen implements Screen
{
	SpriteBatch batch;

	final LightcyclesGame game;
	public final Stage stage;

	OrthographicCamera camera;
	public Viewport viewport;

	int player_count;


	public GameScreen(final LightcyclesGame game, int player_count)
	{
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
		viewport = new StretchViewport(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, camera);

		this.batch = new SpriteBatch();

		batch.setProjectionMatrix(camera.combined);

		this.stage = new Stage(viewport, batch);
		Gdx.input.setInputProcessor(stage);

		this.player_count = player_count;
	}

	public void render(float delta)
	{
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//draw stuff
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void dispose()
	{

	}

	public void hide()
	{

	}

	public void resume()
	{

	}

	public void pause()
	{

	}

	public void resize(int width, int height)
	{

	}

	public void show()
	{

	}
}
