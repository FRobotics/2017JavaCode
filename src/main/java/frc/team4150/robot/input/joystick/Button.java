package main.java.frc.team4150.robot.input.joystick;

/**
 * Represents the buttons on the black controller we use
 * All of the buttons are mapped here
 */
public enum Button {
	A(1),
    B(2),
    X(3),
    Y(4),
    LEFT_BUMPER(5),
    RIGHT_BUMPER(6),
    BACK(7),
    START(8)
    ;

    private int id;

    Button(int id){
        this.id = id;
    }

    /**
     * @return the id of this button
     */
    public int getId() {
        return id;
    }

}
