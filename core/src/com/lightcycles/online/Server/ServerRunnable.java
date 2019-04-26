package com.lightcycles.online.Server;

import com.lightcycles.online.Game.LightcyclesGame;
import com.lightcycles.online.Server.Server;

public class ServerRunnable implements Runnable
{
	LightcyclesGame game;

	public ServerRunnable(LightcyclesGame game)
	{
		this.game = game;
	}

	@Override
	public void run()
	{
		Server server = new Server(game);
	}
}
