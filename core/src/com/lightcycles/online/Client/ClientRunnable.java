package com.lightcycles.online.Client;

import com.lightcycles.online.Client.Client;

public class ClientRunnable implements Runnable
{
	boolean isPlayer;

	public ClientRunnable(boolean isPlayer)
	{
		this.isPlayer = isPlayer;
	}

	@Override
	public void run()
	{
		Client client = new Client(isPlayer);
	}
}
