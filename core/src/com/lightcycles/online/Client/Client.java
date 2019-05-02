package com.lightcycles.online.Client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lightcycles.online.Game.LightcyclesGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client extends Actor
{
	final LightcyclesGame game;

	boolean isPlayer;
	int player_num;

	SocketHints shints;
	Socket socket;

	char last_direction;

	public Client(boolean isPlayer, LightcyclesGame game, String ip)
	{
		this.isPlayer = isPlayer;

		this.game = game;

		shints = new SocketHints();
		socket = Gdx.net.newClientSocket(Net.Protocol.TCP, ip, 17893, shints);

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
	}

	public void queryInput()
	{
		try {
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if (last_direction == 'r') return;
				socket.getOutputStream().write("r\n".getBytes());
				last_direction = 'r';
			} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				if (last_direction == 'd') return;
				socket.getOutputStream().write("d\n".getBytes());
				last_direction = 'd';
			} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if (last_direction == 'l') return;
				socket.getOutputStream().write("l\n".getBytes());
				last_direction = 'l';
			} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				if (last_direction == 'u') return;
				socket.getOutputStream().write("u\n".getBytes());
				last_direction = 'u';
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		queryInput();
	}
}