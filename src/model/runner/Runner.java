package model.runner;

import java.awt.Point;

public class Runner {

	private  Point position;
	private double speed;
	private long scores = 0;
	private int lives = 3;
	private int  numFirstTypeGift = 0;
	private int  numSecondTypeGift = 0;

	Weapon weapon =  Weapon.getInstance();
	//static member holds only one instance of the runner class
	private static Runner player;
	
	// Runner prevents the instantiation from any other class.
	private Runner(){}
	//Now we are providing gloabal point of access.
    public static Runner getInstance() {
                                if (player==null)
                              {
                                       player=new  Runner();
                              }
                    return player;
        }
	public Point getPosition() {
		return position;
	}
	public void setPosition(final Point position) {
		this.position = position;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(final double speed) {
		this.speed = speed;
	}
	public long getScores() {
		return scores;
	}
	public void setScores(final long scores) {
		this.scores = scores;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(final int lives) {
		this.lives = lives;
	}
	public boolean weaponUse(){
		if(weapon.getBullets()>0 ){
			return true;
		}
		return false;

	}
	public boolean isLife(){
		if(this.getLives()>0){
			return true;
		}
		else{
			return false;
		}

	}
	public int getNumFirstTypeGift() {
		return numFirstTypeGift;
	}
	public void setNumFirstTypeGift(final int numFirstTypeGift) {
		this.numFirstTypeGift = numFirstTypeGift;
	}
	public int getNumSecondTypeGift() {
		return numSecondTypeGift;
	}
	public void setNumSecondTypeGift(final int numSecondTypeGift) {
		this.numSecondTypeGift = numSecondTypeGift;
	}
}
