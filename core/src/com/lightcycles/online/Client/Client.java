package com.lightcycles.online.Client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.lightcycles.online.Game.LightcyclesGame;
import org.lwjgl.input.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client
{
	final LightcyclesGame game;

	boolean isPlayer;
	int player_num;

	SocketHints shints;
	Socket socket;

	public Client(boolean isPlayer, LightcyclesGame game)
	{
		this.isPlayer = isPlayer;

		this.game = game;

		shints = new SocketHints();
		socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 17893, shints);

		try {
			socket.getOutputStream().write((isPlayer ? "player\n" : "better\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		try {
			String line = buff.readLine();
			int player_num = Integer.parseInt(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("I'm client number " + player_num + "!");

		queryInput();
	}

	public void queryInput()
	{
		try {
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
					socket.getOutputStream().write("r".getBytes());
			} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				socket.getOutputStream().write("d".getBytes());
			} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				socket.getOutputStream().write("l".getBytes());
			} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				socket.getOutputStream().write("u".getBytes());
			}
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
}