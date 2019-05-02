package com.lightcycles.online.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.lightcycles.online.Client.ClientRunnable;
import com.lightcycles.online.Server.ServerRunnable;
import com.lightcycles.online.Settings;

public class LightcyclesGameClient extends LightcyclesGame
{

	public GameScreen gameScreen;
	String ip;

	public LightcyclesGameClient(String ip)
	{
		this.ip = ip;
	}

	@Override
	public void create()
	{
		Gdx.graphics.setWindowedMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

		this.gameScreen = new GameScreen(this, 1);
		this.setScreen(gameScreen);

		Thread clientThread = new Thread(new ClientRunnable(true, this, ip));
		clientThread.start();

		LightcycleGameSimulation simulation = new LightcycleGameSimulation(gameScreen);
	}

	public void render()
	{
		super.render();
	}

	public void dispose()
	{

	}
}
