package com.lightcycles.online.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lightcycles.online.Client.ClientRunnable;
import com.lightcycles.online.Client.InputPointer;
import com.lightcycles.online.Server.ServerRunnable;
import com.lightcycles.online.Settings;

import java.util.HashMap;
import java.util.Map;

public class LightcyclesGameClient extends LightcyclesGame
{

	public GameScreen gameScreen;
	String ip;
	Map<Integer, Character> input_map = new HashMap<>();

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

		Stage stage = this.gameScreen.stage;

		Thread clientThread = new Thread(new ClientRunnable(true, this, ip, stage));
		clientThread.start();

		InputPointer inpy = new InputPointer();
		inpy.input_char = 'n';

		LightcycleGameSimulation simulation = new LightcycleGameSimulation(gameScreen, input_map, inpy);
	}

	public void render()
	{
		super.render();
	}

	public void dispose()
	{

	}
}
