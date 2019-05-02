package com.lightcycles.online.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.lightcycles.online.Client.ClientRunnable;
import com.lightcycles.online.Server.Server;
import com.lightcycles.online.Server.ServerRunnable;
import com.lightcycles.online.Settings;

import java.util.HashMap;
import java.util.Map;

public class LightcyclesGame extends Game
{

	public GameScreen gameScreen;
	Map<Integer, Character> input_map = null;

	@Override
	public void create()
	{
		Gdx.graphics.setWindowedMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

		this.gameScreen = new GameScreen(this, 1);
		this.setScreen(gameScreen);

		this.input_map = new HashMap<>();

		Thread serverThread = new Thread(new ServerRunnable(this, input_map));
		serverThread.start();

		LightcycleGameSimulation simulation = new LightcycleGameSimulation(gameScreen, input_map);
	}

	public void render()
	{
		super.render();
	}

	public void dispose()
	{

	}
}
