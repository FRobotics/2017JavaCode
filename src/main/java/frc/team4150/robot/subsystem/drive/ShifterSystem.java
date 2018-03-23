package main.java.frc.team4150.robot.subsystem.drive;

import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class ShifterSystem extends SubsystemBase {
	
	private DoubleSolenoidSystem solenoid;
	private Direction highDirection;

	public ShifterSystem(int forwardChannel, int reverseChannel, Direction defaultDirection, Direction highDirection) {
		this.solenoid = new DoubleSolenoidSystem(forwardChannel, reverseChannel, defaultDirection);
		this.highDirection = highDirection;
	}
	
	public void setShift(boolean high) {
		if(high) solenoid.setDirection(this.highDirection);
		else solenoid.setDirection(this.highDirection.getOpposite());
	}
	
	public void shift() {
		solenoid.flipDirection();
	}

	@Override
	public void init() {
		solenoid.init();
	}

	@Override
	public void periodic() {
		solenoid.periodic();
	}
	
	public boolean isHigh() {
		return solenoid.getDirection() == highDirection;
	}
	
	public boolean isLow() {
		return solenoid.getDirection() != highDirection;
	}

}
