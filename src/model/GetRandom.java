package model;

import java.util.ArrayList;
import java.util.Random;

import model.iterator.Iterator;
import model.iterator.RandomNumbers;

public class GetRandom implements Strategy1 {

	@Override
	public void random(final ArrayList<ArrayList<Character>> maze1, final RandomNumbers num, final char c) {
		final Iterator iter = num.getIterator();
		while(iter.hasNext()){
			iter.next();
		     //Get random position for the next bomb
		     final Random rand = new Random();
		     int row = rand.nextInt(maze1.size());
		     int col= rand.nextInt(maze1.get(0).size());
		     // don't set at the begin or the end
		     while((row == 1 && col == 1) || (row == maze1.size()-2 && col == maze1.get(0).size()-2)
		    		 || row == 0 || col == 0 || row == maze1.size()-1 || col == maze1.get(0).size()-1) {
		    	        //we get new position
		    	 row = rand.nextInt(maze1.size());
		         col = rand.nextInt(maze1.get(0).size());
		     }
		     if(c == 'T' && col != 0 && maze1.get(row).get(col).equals('+')) {
		    	 maze1.get(row).set(col, 'T');
		     }
		     else if(maze1.get(row).get(col).equals(' ')){
		    	 maze1.get(row).set(col, c);
		     }
		     else {
		    	iter.previous();
		     }
		}
	}
}
