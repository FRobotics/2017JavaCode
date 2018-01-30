package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class DoubleSolenoidSystem extends SubsystemBase {

    public enum Direction {
        FORWARD(Value.kForward),
        REVERSE(Value.kReverse),
        OFF(Value.kOff);
        private Value value;

        Direction(Value value) {
            this.value = value;
        }

        public Value getValue() {
            return value;
        }

        public Direction getOpposite() {
            switch (value) {
                case kForward:
                    return REVERSE;
                case kReverse:
                    return FORWARD;
			default:
				return OFF;
            }
        }
    }

    private Direction defaultDirection;
    private DoubleSolenoid doubleSolenoid;

    public DoubleSolenoidSystem(int forwardChannel, int reverseChannel, Direction defaultDirection) {
        this.doubleSolenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
        this.defaultDirection = defaultDirection;
    }

    @Override
    public void init() {
        doubleSolenoid.set(defaultDirection.getValue());
    }

    /**
     * Reverses the direction of the solenoid. If off, sets it to the default direction
     */
    public void flipDirection() {
        switch (doubleSolenoid.get()) {
            case kOff:
                doubleSolenoid.set(this.defaultDirection.value);
                break;
            case kForward:
                doubleSolenoid.set(Value.kReverse);
                break;
            case kReverse:
                doubleSolenoid.set(Value.kForward);
                break;
        }
    }

    /**
     * Sets the directions of the solenoid
     * @param direction
     */
    public void setDirection(Direction direction) {
        doubleSolenoid.set(direction.getValue());
    }

	@Override
	public void periodic() {
		
	}
}
