package main.java.frc.team4150.robot.subsystem.motor;

import edu.wpi.first.wpilibj.PWMSpeedController;

public abstract class PWMSystem extends MotorSystem {

	private PWMSpeedController motor;
	
	public PWMSystem (PWMSpeedController motor) {
		this.motor = motor;
	}
	
	@Override
	public void init() {
		motor.setSafetyEnabled(true);
		motor.setExpiration(0.1);
	}

	@Override
	public void periodic() {
		super.periodic();
		motor.setSpeed(getSpeed());
	}
}
