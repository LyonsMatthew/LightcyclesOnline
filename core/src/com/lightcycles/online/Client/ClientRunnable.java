package com.lightcycles.online.Client;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lightcycles.online.Client.Client;
import com.lightcycles.online.Game.LightcyclesGame;
import com.lightcycles.online.Game.LightcyclesGameClient;

import java.util.List;

public class ClientRunnable implements Runnable
{
	Stage stage;
	Client clint;

	public ClientRunnable(Stage stage, Client client)
	{
		this.stage = stage;
		this.clint = client;
		LightcyclesGameClient.client = client;
	}

	@Override
	public void run()
	{
		stage.addActor(clint);
	}
}
