package main.java.frc.team4150.robot.input.joystick;

/**
 * Represents an axis for a joy stick
 * @author cat16
 *
 */
public enum Axis {
    LEFT_X(0),
    LEFT_Y(1),
    TRIGGER_LEFT(2),
    TRIGGER_RIGHT(3),
    RIGHT_X(4),
    RIGHT_Y(5);
	
	private int id;
	
	Axis(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
