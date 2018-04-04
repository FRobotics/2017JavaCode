package main.java.frc.team4150.robot.subsystem.motor;

import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.util.Util;

public abstract class MotorSystem extends SubsystemBase {

	public static final double RATE_LIMIT = 0.05;
	private double targetSpeed;
	private double currentSpeed;
	
	public MotorSystem() {
		currentSpeed = 0;
	}
	
	public void setSpeed(double speed) {
		this.targetSpeed = Util.limit(speed);
	}
	
	public void forceSetSpeed(double speed) {
		this.targetSpeed = Util.limit(speed);
		this.currentSpeed = Util.limit(speed);
	}
	
	public void stop() {
		this.setSpeed(0);
	}
	
	public void forceStop() {
		this.forceSetSpeed(0);
	}
	
	public double getSpeed() {
		return currentSpeed;
	}
	
	public void reverse() {
		targetSpeed *= -1;
	}
	
	@Override
	public void periodic() {
		if(targetSpeed > currentSpeed) {
			currentSpeed = Math.min(currentSpeed + RATE_LIMIT, targetSpeed);
		} else if (targetSpeed < currentSpeed) {
			currentSpeed = Math.max(currentSpeed - RATE_LIMIT, targetSpeed);
		}
	}

}
