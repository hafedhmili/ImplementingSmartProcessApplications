/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * Author: Hafedh Mili
 */
public interface HealthParameterScore {
	
	public HealthParameter getParameter();
	
	public ScoreValue getScore();
	
	public void setScore(ScoreValue value);

}
