package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.PWMSpeedController;

public abstract class PWMSpeedControllerSystem extends MotorSystem {

	private PWMSpeedController motor;
	
	public PWMSpeedControllerSystem (PWMSpeedController motor) {
		this.motor = motor;
	}
	
	@Override
	public void init() {
		motor.setSafetyEnabled(true);
		motor.setExpiration(0.1);
	}

	@Override
	public void periodic() {
		motor.setSpeed(getSpeed());
	}

}
