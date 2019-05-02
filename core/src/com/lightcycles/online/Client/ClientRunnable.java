package com.lightcycles.online.Client;

import com.lightcycles.online.Client.Client;
import com.lightcycles.online.Game.LightcyclesGame;

public class ClientRunnable implements Runnable
{
	boolean isPlayer;
	LightcyclesGame game;
	String ip;

	public ClientRunnable(boolean isPlayer, LightcyclesGame game, String ip)
	{
		this.isPlayer = isPlayer;
		this.game = game;
		this.ip = ip;
	}

	@Override
	public void run()
	{
		Client client = new Client(isPlayer, game, ip);
	}
}
