package com.lightcycles.online.Client;

import com.lightcycles.online.Client.Client;
import com.lightcycles.online.Game.LightcyclesGame;

public class ClientRunnable implements Runnable
{
	boolean isPlayer;
	LightcyclesGame game;

	public ClientRunnable(boolean isPlayer, LightcyclesGame game)
	{
		this.isPlayer = isPlayer;
		this.game = game;
	}

	@Override
	public void run()
	{
		Client client = new Client(isPlayer, game);
	}
}
