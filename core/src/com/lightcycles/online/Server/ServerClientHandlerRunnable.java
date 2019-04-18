package com.lightcycles.online.Server;

public class ServerClientHandlerRunnable implements Runnable
{
	Server server;
	int clientIndex;
	boolean isPlayer;

	public ServerClientHandlerRunnable(Server server, int clientIndex, boolean isPlayer)
	{
		this.server = server;
		this.clientIndex = clientIndex;
		this.isPlayer = isPlayer;
	}

	@Override
	public void run()
	{
		server.manageClient(clientIndex, isPlayer);
	}
}
