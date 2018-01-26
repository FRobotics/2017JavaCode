package main.java.frc.team4150.robot.util.equation;

public class SpeedDistanceTimeEquation {
	
	public enum Name {
		SPEED, DISTANCE, TIME
	}
	
	private double speed;
	private double distance;
	private double time;
	
	public SpeedDistanceTimeEquation(SpeedDistanceTimeVariable x, SpeedDistanceTimeVariable y) {
		if(x.getName() == y.getName()) {
			speed = 0;
			distance = 0;
			time = 0;
			System.out.println("WARNING: Variables were set to 0 due to the same name");
			//TODO: crash here instead of ignoring it
			return;
		}
		set(x, y);
	}
	
	/**
	 * Sets a variable and adjusts another based on the constant
	 * @param set
	 * @param change
	 */
	public void set(SpeedDistanceTimeVariable set, Name change) {
		Name name = set.getName();
		if(name == change) return;
		if(name == Name.SPEED && change == Name.TIME) { set(set); time = distance / speed; return; }
		if(name == Name.SPEED && change == Name.DISTANCE) { set(set); distance = speed * time; return; }
		if(name == Name.DISTANCE && change == Name.TIME) { set(set); time = distance / speed; return; }
		if(name == Name.DISTANCE && change == Name.SPEED) { set(set); speed = distance / time; return; }
		if(name == Name.TIME && change == Name.DISTANCE) { set(set); distance = speed * time; return; }
		if(name == Name.TIME && change == Name.SPEED) { set(set); speed = distance / time; return; }
	}
	
	/**
	 * Sets two variables and changes the third
	 * @param x
	 * @param y
	 */
	public void set(SpeedDistanceTimeVariable x, SpeedDistanceTimeVariable y) {
		if(
				(x.getName() == Name.DISTANCE && y.getName() == Name.SPEED) ||
				(x.getName() == Name.SPEED && y.getName() == Name.DISTANCE)
		) {
			set(x);
			set(y, Name.TIME);
		}
		if(
				(x.getName() == Name.DISTANCE && y.getName() == Name.TIME) ||
				(x.getName() == Name.TIME && y.getName() == Name.DISTANCE)
		) {
			set(x);
			set(y, Name.SPEED);
		}
		if(
				(x.getName() == Name.TIME && y.getName() == Name.SPEED) ||
				(x.getName() == Name.SPEED && y.getName() == Name.TIME)
		) {
			set(x);
			set(y, Name.DISTANCE);
		}
	}
	
	/**
	 * Sets a variable, but doesn't change the others
	 * @param change
	 */
	private void set(SpeedDistanceTimeVariable change) {
		switch(change.getName()) {
			case SPEED:
				speed = change.value;
				break;
			case DISTANCE:
				distance = change.value;
				break;
			case TIME:
				time = change.value;
				break;
		}
	}
	
	/**
	 * Gets the value of a variable in the equation
	 * @param name
	 * @return
	 */
	public double get(Name name) {
		switch(name) {
			case SPEED:
				return speed;
			case DISTANCE:
				return distance;
			case TIME:
				return time;
		}
		return 0;
	}
}
