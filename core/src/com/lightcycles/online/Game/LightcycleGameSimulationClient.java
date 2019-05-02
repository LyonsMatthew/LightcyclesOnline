package com.lightcycles.online.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lightcycles.online.Client.InputPointer;
import com.lightcycles.online.Settings;

import java.util.ArrayList;
import java.util.Map;

public class LightcycleGameSimulationClient extends LightcycleGameSimulation
{
	GameScreen gameScreen;
	ArrayList<Lightcycle> lightcycles;

	Texture texture;
	int[][] paths;
	Path[][] pathSprites;

	LightcycleTimer timer;

	Map<Integer, Character> input_map;
	InputPointer inpy;

	public LightcycleGameSimulationClient(GameScreen gameScreen, Map<Integer, Character> input_map, InputPointer inpy)
	{
		super(gameScreen, input_map, inpy);
		this.gameScreen = gameScreen;
		this.input_map = input_map;
		this.inpy = inpy;

		this.texture = new Texture(Gdx.files.internal("path.png"));
		paths = new int[Settings.GRID_HEIGHT][Settings.GRID_WIDTH];
		pathSprites = new Path[Settings.GRID_HEIGHT][Settings.GRID_WIDTH];

		for(int i=0;i<Settings.GRID_HEIGHT;i++)
		{
			for(int j=0;j<Settings.GRID_WIDTH;j++)
			{
				paths[i][j] = -1;
				pathSprites[i][j] = new Path(this.texture, j, i);
				gameScreen.stage.addActor(pathSprites[i][j]);
			}
		}

		this.lightcycles = new ArrayList<>();
		this.timer = new LightcycleTimer(Settings.TICK_LENGTH);
		timer.start();

		gameScreen.stage.addActor(this);

		while (this.inpy.input_char == 'n');
		for(int i=0;i<gameScreen.player_count;i++) {
			lightcycles.add(new Lightcycle(i));
			gameScreen.stage.addActor(lightcycles.get(i));
		}
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
