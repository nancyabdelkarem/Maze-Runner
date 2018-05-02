package model.iterator;

public class RandomNumbers implements Container {
	public int randomNumbers;

 public RandomNumbers(int randomNumbers) {
	this.randomNumbers = randomNumbers;
}
	 @Override
	public Iterator getIterator() {
	      return new NumberIterator();
	   }
	 private class NumberIterator implements Iterator {
	      int index=0;

	      @Override
	      public boolean hasNext() {
	         if(index < randomNumbers){
	            return true;
	         }
	         return false;
	      }

	      @Override
	      public Object next() {
	         if(this.hasNext()){
	            return index++;
	         }
	         return null;
	      }

	      @Override
		public boolean hasPrevious() {
		         if(index > 0){
		            return true;
		         }
		         return false;
		      }

		@Override
		public Object previous() {
		        if(this.hasPrevious()){
		           return index--;
		        }
		        return null;
		     }
	   }
}
