package com.lightcycles.online.Client;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lightcycles.online.Client.Client;
import com.lightcycles.online.Game.LightcyclesGame;

public class ClientRunnable implements Runnable
{
	boolean isPlayer;
	LightcyclesGame game;
	String ip;
	Stage stage;

	public ClientRunnable(boolean isPlayer, LightcyclesGame game, String ip, Stage stage)
	{
		this.isPlayer = isPlayer;
		this.game = game;
		this.ip = ip;
		this.stage = stage;
	}

	@Override
	public void run()
	{
		Client client = new Client(isPlayer, game, ip);
		stage.addActor(client);
	}
}
