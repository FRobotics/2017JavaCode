package main.java.frc.team4150.robot.subsystem.motor;

import main.java.frc.team4150.robot.subsystem.DigitalInputSystem;

public class LimitedMotorSystem extends MotorSystem {
	
	private MotorSystem motor;
	private DigitalInputSystem forwardLimit, reverseLimit;
	
	public LimitedMotorSystem(MotorSystem motor, int forwardLimitPort, int reverseLimitPort) {
		this.motor = motor;
		forwardLimit = new DigitalInputSystem(forwardLimitPort);
		reverseLimit = new DigitalInputSystem(reverseLimitPort);
	}

	@Override
	public void init() {
		motor.init();
		forwardLimit.init();
		reverseLimit.init();
	}

	@Override
	public void periodic() {
		this.motor.periodic();
		this.forwardLimit.periodic();
		this.reverseLimit.periodic();
		if (forwardLimit.triggered()) {
			if(motor.getSpeed() > 0) motor.stop();
		} else if (reverseLimit.triggered()) {
			if(motor.getSpeed() < 0) motor.stop();
		}
	}
	
	public MotorSystem getMotor() {
		return motor;
	}
}
