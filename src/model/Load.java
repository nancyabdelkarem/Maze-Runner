package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Load {
	private static Load load;
	ArrayList<ArrayList<Character>> maze1=new ArrayList<ArrayList<Character>>();

	public static Load getInstance() {
        if (load == null){
               load = new Load();
        }
        return load;
	}

	public ArrayList<ArrayList<Character>> loadMaze() {
		String file = "Maze.txt";
		File file1 = new File(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = br.readLine()) != null) {
				ArrayList<Character> row = new ArrayList<>();
				for(int j = 0; j < s.length(); j++) {
					row.add(s.charAt(j));
				}
				maze1.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.delete(file1.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return maze1;
	}

	public ArrayList<Integer> loadDetails() {
		String file = "Details.txt";
		File file1 = new File(file);
		ArrayList<Integer> row = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = br.readLine()) != null) {
				row.add(Integer.parseInt(s));
			}
			System.out.println(row);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.delete(file1.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
}
