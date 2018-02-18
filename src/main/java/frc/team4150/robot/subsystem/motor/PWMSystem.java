package main.java.frc.team4150.robot.subsystem.motor;

import edu.wpi.first.wpilibj.PWMSpeedController;

public abstract class PWMSystem extends MotorSystem {

	private PWMSpeedController motor;
	private double currentSpeed;
	public static final double RATE_LIMIT = 0.1;
	
	public PWMSystem (PWMSpeedController motor) {
		this.motor = motor;
		currentSpeed = 0;
	}
	
	@Override
	public void init() {
		motor.setSafetyEnabled(true);
		motor.setExpiration(0.1);
	}

	@Override
	public void periodic() {
		if(getSpeed() > currentSpeed) {
			currentSpeed = Math.min(currentSpeed + RATE_LIMIT, getSpeed());
		} else if (getSpeed() < currentSpeed) {
			currentSpeed = Math.max(currentSpeed - RATE_LIMIT, getSpeed());
		}
		motor.setSpeed(currentSpeed);
	}
}
