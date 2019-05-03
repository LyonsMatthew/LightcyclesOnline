package com.lightcycles.online.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lightcycles.online.Client.InputPointer;
import com.lightcycles.online.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class LightcycleGameSimulationClient extends LightcycleGameSimulation
{

	public LightcycleGameSimulationClient(GameScreen gameScreen, Map<Integer, Character> input_map, List<InputPointer> inpy)
	{
		super(gameScreen, input_map, inpy);
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

	}

	public void simulateSingleStep()
	{

	}

	public void checkDeath(int player_num)
	{

	}

	public void checkEndGame()
	{

	}
}
