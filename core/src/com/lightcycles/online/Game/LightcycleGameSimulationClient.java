package com.lightcycles.online.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lightcycles.online.Client.Client;
import com.lightcycles.online.Client.InputPointer;
import com.lightcycles.online.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class LightcycleGameSimulationClient extends LightcycleGameSimulation
{

	Client client;
	BufferedReader buff;

	public LightcycleGameSimulationClient(GameScreen gameScreen, Map<Integer, Character> input_map, List<InputPointer> inpy, Client client)
	{
		super(gameScreen, input_map, inpy, null);
		this.client = client;
		new Thread() {
			public void run() {
				set_initial_position();
			}
		}.start();
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		Color color_swap = new Color();
		for(int i=0;i<Settings.GRID_HEIGHT;i++)
		{
			for(int j=0;j<Settings.GRID_WIDTH;j++)
			{
				color_swap.set(batch.getColor());
				int pathPlayerNum = paths[i][j];
				if (pathPlayerNum == -1)
				{
					batch.setColor(new Color(0, 0, 0, 0));
				}
				else
				{
					batch.setColor(lightcycles.get(pathPlayerNum).get_color());
				}
				pathSprites[i][j].draw(batch, parentAlpha);
				batch.setColor(color_swap);
			}
		}
	}

	@Override
	public void act(float deltaTime)
	{
		super.act(deltaTime);
	}

	@Override
	public void simulateSingleStep()
	{
		int i = 0;
		for(int pnum : this.input_map.keySet())
		{
			try {
				String new_pos = buff.readLine();
				move_based_on_input(pnum, new_pos);
				int old_x = lightcycles.get(pnum).grid_x;
				int old_y = lightcycles.get(pnum).grid_y;
//				lightcycles.get(pnum).move();
				paths[old_y][old_x] = pnum;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void move_based_on_input(int pnum, String new_pos)
	{
		Lightcycle cycle = this.lightcycles.get(pnum);
		int old_x = cycle.get_grid_x();
		int old_y = cycle.get_grid_y();
		String[] parts = new_pos.split(",");
		int new_x = Integer.parseInt(parts[0]);
		int new_y = Integer.parseInt(parts[1]);
		int true_pnum = Integer.parseInt(parts[2].substring(0, parts[2].length()));
		char move = 'o';
		if (old_x - new_x == 1) move = 'l';
		else if (old_x - new_x == -1) move = 'r';
		else if (old_y - new_y == 1) move = 'd';
		else if (old_y - new_y == -1) move = 'u';
		input_map.put(true_pnum, move);
		lightcycles.get(true_pnum).set_action(move);
		lightcycles.get(true_pnum).grid_x = new_x;
		lightcycles.get(true_pnum).grid_y = new_y;
		lightcycles.get(true_pnum).set_position();
		if (client.player_num == true_pnum) client.last_direction = move;
	}

	public void checkDeath(int player_num)
	{

	}

	public void checkEndGame()
	{
	}

	public void set_initial_position()
	{
		while (this.inpy.get(0).input_char == 'n' || this.inpy.get(2).input_char == 'n')
		{
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buff = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
		for(Lightcycle l : this.lightcycles)
		{
			try {
				String new_pos = buff.readLine();
				set_pos_based_on_input(l.player_num, new_pos);
				input_map.put(l.player_num, 'r');
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.inpy.get(3).input_char = 'y';
	}

	private void set_pos_based_on_input(int pnum, String new_pos)
	{
		Lightcycle cycle = this.lightcycles.get(pnum);
		String[] parts = new_pos.split(",");
		int new_x = Integer.parseInt(parts[0]);
		int new_y = Integer.parseInt(parts[1].substring(parts[1].length()-1));
		cycle.grid_x = new_x;
		cycle.grid_y = new_y;
		paths[new_y][new_x-1] = pnum;
	}
}
