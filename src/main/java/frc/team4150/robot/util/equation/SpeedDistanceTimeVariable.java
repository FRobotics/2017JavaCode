package main.java.frc.team4150.robot.util.equation;

public class SpeedDistanceTimeVariable {
	
	/**
	 * The value of this variable
	 */
	public double value;
	private SpeedDistanceTimeEquation.Name name;
	
	public SpeedDistanceTimeVariable(SpeedDistanceTimeEquation.Name name, double value) {
		this.value = value;
		this.name = name;
	}
	
	/**
	 * @return the name of this variable
	 */
	public SpeedDistanceTimeEquation.Name getName() {
		return name;
	}
	
}
