package com.lightcycles.online.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lightcycles.online.Client.ClientRunnable;
import com.lightcycles.online.Client.InputPointer;
import com.lightcycles.online.Server.ServerRunnable;
import com.lightcycles.online.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

		List<InputPointer> inpy = new ArrayList<>();
		inpy.add(new InputPointer('o'));
		inpy.add(new InputPointer((char) 0));
		inpy.get(0).input_char = 'y';

		this.gameScreen = new GameScreen(this, inpy);
		this.setScreen(gameScreen);

		Stage stage = this.gameScreen.stage;

		Thread clientThread = new Thread(new ClientRunnable(true, this, ip, stage));
		clientThread.start();

		LightcycleGameSimulation simulation = new LightcycleGameSimulationClient(gameScreen, input_map, inpy);
	}

	public void render()
	{
		super.render();
	}

	public void dispose()
	{

	}
}
