package main.java.frc.team4150.robot.subsystem.motor;

import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.util.Util;

public abstract class MotorSystem extends SubsystemBase {

	private double speed;
	
	public void setSpeed(double speed) {
		this.speed = Util.limit(speed);
	}
	
	public void stop() {
		this.setSpeed(0);
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void reverse() {
		speed *= -1;
	}
	
	@Override
	public abstract void init();

	@Override
	public abstract void periodic();

}
