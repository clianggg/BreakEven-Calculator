public class IntersectionCalculator{ 
	
	//Properties
	/**This property is a double variable specifying the x-coordinate of the point of intersection of two linear functions
	 */
	public double dblX;
	/**This property is a double variable specifying the y-coordinate of the point of intersection of two linear functions
	 */
	public double dblY;
	/**This is an integer specifying the m value of Line1, it is defaulted to 0
	 */
	public int intM1 = 0;
	/**This is an integer specifying the m value of Line2, it is defaulted to 0
	 */
	public int intM2 = 0;
	/**This is an integer specifying the b value of Line1, it is defaulted to 0
	 */
	public int intB1 = 0;
	/**This is an integer specifying the b value of Line2, it is defaulted to 0
	 */
	public int intB2 = 0;
	
	//Methods
	/**Calculates and returns the x-coordinate of the point of intersection, rounded to 2 decimal places
	 * @return a double value for the x-coordinate of the point of intersection
	 */
	public double calcX(){
		 double dblPreRoundX = (double)(intB2 - intB1) / (intM1 - intM2);
		 dblX = Math.round(dblPreRoundX*100.0)/100.0;
		 return dblX;
	}
	/**Calculates and returns the y-coordinate of the point of intersection, rounded to 2 decimal places
	 * @return a double value for the y-coordinate of the point of intersection
	 */
	public double calcY(){
		double dblPreRoundY = (double)intM1*dblX + intB1;
		dblY = Math.round(dblPreRoundY*100.0)/100.0;
		return dblY;
	}
	/**Checks if the two linear functions are parallel and return true if it is
	 * @return true if the linear functions are parallel, false if it isn't
	 */
	public boolean isParallel(){
		if(intM1 == intM2){
			return true;
		}else{
			return false;
		}
	}
	/**Resets all properties to zero
	 */
	public void reset(){
		dblY = 0;
		dblX = 0;
		intM1 = 0;
		intM2 = 0;
		intB1 = 0;
		intB2 = 0;
	}
	
}
