package model.runner;

public class Weapon {
private int bullets=6;
private static Weapon weapon;
// Runner prevents the instantiation from any other class.
private Weapon(){}
//Now we are providing gloabal point of access.
	public static Weapon getInstance() {
	                            if (weapon==null)
	                          {
	                                   weapon=new  Weapon();
	                          }
	                return weapon;
	    }

	public int getBullets() {
		return bullets;
	}

	public void setBullets(int bullets) {
		this.bullets = bullets;
	}

}
