package com.lightcycles.online.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.lightcycles.online.Server.Server;
import com.lightcycles.online.Server.ServerRunnable;
import com.lightcycles.online.Settings;

public class LightcyclesGame extends Game
{

	public GameScreen gameScreen;

	@Override
	public void create()
	{
		Gdx.graphics.setWindowedMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

		this.gameScreen = new GameScreen(this, 1);
		this.setScreen(gameScreen);

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
