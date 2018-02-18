package main.java.frc.team4150.robot.subsystem.motor;

import main.java.frc.team4150.robot.subsystem.CounterSystem;
import main.java.frc.team4150.robot.subsystem.DigitalInputSystem;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class LimitedMotorSystem extends SubsystemBase {
	
	private MotorSystem motor;
	private DigitalInputSystem forwardLimit, reverseLimit;
	private CounterSystem counter;
	
	public LimitedMotorSystem(MotorSystem motor, int forwardLimitPort, int reverseLimitPort, int counterPort) {
		this.motor = motor;
		forwardLimit = new DigitalInputSystem(forwardLimitPort);
		reverseLimit = new DigitalInputSystem(reverseLimitPort);
		counter = new CounterSystem(counterPort);
	}

	@Override
	public void init() {
		motor.init();
		forwardLimit.init();
		reverseLimit.init();
		counter.init();
	}

	@Override
	public void periodic() {
		motor.periodic();
		forwardLimit.periodic();
		reverseLimit.periodic();
		counter.update(motor.getSpeed() > 0);
		//if(counter.get() < -24 && motor.getSpeed() < 0) motor.stop();
		//System.out.println(counter.get());
		/*if(forwardLimit.triggered() || reverseLimit.triggered()) {
			if(counter.get() > 24) {
				counter.reset();
				forwardLimit.reset();
				reverseLimit.reset();
			}
			if (forwardLimit.triggered()) {
				if(motor.getSpeed() > 0) {
					motor.stop();
				}
			} else if (reverseLimit.triggered()) {
				if(motor.getSpeed() < 0) motor.stop();
			}
		} else {
			counter.reset();
		}*/
	}
	
	public void setSpeed(double speed) {
		motor.setSpeed(speed);
		this.periodic();
	}
	
	public void stop() {
		setSpeed(0);
	}
	
	public MotorSystem getMotor() {
		return motor;
	}
}
