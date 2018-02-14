package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class SolenoidSystem extends SubsystemBase {

    private boolean defaultDirection;
    private Solenoid solenoid;

    public SolenoidSystem(int forwardChannel, int reverseChannel, boolean defaultDirection) {
        this.solenoid = new Solenoid(forwardChannel, reverseChannel);
        this.defaultDirection = defaultDirection;
    }

    @Override
    public void init() {
        solenoid.set(defaultDirection);
    }

    /**
     * Reverses the direction of the solenoid. If off, sets it to the default direction
     */
    public void toggle() {
        solenoid.set(!solenoid.get());
    }

    /**
     * Sets the directions of the solenoid
     * @param direction
     */
    public void set(boolean on) {
        solenoid.set(on);
    }
    
    public boolean get() {
    	return solenoid.get();
    }

	@Override
	public void periodic() {
		
	}
}
