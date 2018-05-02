package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Control.Handler;
import model.runner.Runner;
import model.runner.Weapon;


public class Save {
	static ArrayList<ArrayList<Character>> maze1=new ArrayList<ArrayList<Character>>();
	private static Save save;
	Handler handler = MazeGenerator.getHandler();
	Runner player = Runner.getInstance();
	Weapon weapon = Weapon.getInstance();

	public static Save getInstance() {
        if (save == null){
               save = new Save();
        }
        return save;
	}

	public void saveMaze(ArrayList<ArrayList<Character>> maze1) {
		FileWriter writer;
		try {
			writer = new FileWriter("Maze.txt", false);
			for (int i = 0; i < maze1.size(); i++) {
				for(int j = 0; j < maze1.get(0).size(); j++) {
					writer.write(maze1.get(i).get(j));
				}
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void saveDetails() {
		FileWriter writer;
		try {
			writer = new FileWriter("Details.txt", false);
			writer.write(Integer.toString(player.getLives())+"\n");
			writer.write(Long.toString(player.getScores())+"\n");
			writer.write(Integer.toString(player.getPosition().x)+"\n");
			writer.write(Integer.toString(player.getPosition().y)+"\n");
			writer.write(Integer.toString(player.getNumFirstTypeGift())+"\n");
			writer.write(Integer.toString(player.getNumSecondTypeGift())+"\n");
			writer.write(Integer.toString(weapon.getBullets())+"\n");
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
