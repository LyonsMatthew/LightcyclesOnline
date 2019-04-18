package com.lightcycles.online;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lightcycles.online.Client.ClientRunnable;
import com.lightcycles.online.Server.ServerRunnable;

public class LightcyclesOnline extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		Thread serverThread = new Thread(new ServerRunnable());
		serverThread.start();
		Thread clientThread = new Thread(new ClientRunnable(true));
		clientThread.start();
		clientThread = new Thread(new ClientRunnable(true));
		clientThread.start();
		clientThread = new Thread(new ClientRunnable(false));
		clientThread.start();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
