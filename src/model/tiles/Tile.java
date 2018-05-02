package model.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles = new Tile[265];
	public static Tile floorTile = new FloorTile(0);
	public static Tile stoneTile = new StoneTile(2);
	
	public static final int TILE_WIDTH = 42;
	public static final int TILE_LENGTH = 42;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture,int id){
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	public void tick(){
		
	}
	public void render(Graphics g,int x,int y){
		g.drawImage(texture,x,y,TILE_WIDTH,TILE_LENGTH,null);
		//g.drawImage(Assets.gate,x,y,TILE_WIDTH,TILE_LENGTH,null);
	}
	public boolean isSolid(){
		return false;
	}
	public int getID(){
		return id;
	}

}
