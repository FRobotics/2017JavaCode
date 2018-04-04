package main.java.frc.team4150.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionControl {

	private final double target;
	private final double minSpeed;
	private final double maxSpeed;
	private final double rate;
	private final double deadband;
	private final boolean startMax;
	private final double threshold;
	private int onTargetCount;
	private static final int ON_TARGET_GOAL = 3;
	private double speed;
	private double slope;

	public PositionControl(double target, double minSpeed, double maxSpeed, double rate, double deadband, boolean startMax, double threshold) {
		this.target = target;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.rate = rate;
		this.deadband = deadband;
		this.startMax = startMax;
		this.threshold = threshold;
		this.onTargetCount = 0;
		this.speed = 0;
		this.slope = ((maxSpeed - minSpeed)/(threshold - deadband));
	}

	public PositionControl(double target, String key, boolean startMax, double threshold) {
		this(	target, SmartDashboard.getNumber(key + "PosControl/minSpeed", 0.2),
				SmartDashboard.getNumber(key + "PosControl/maxSpeed", 0.8),
				SmartDashboard.getNumber(key + "PosControl/rate", 0.1),
				SmartDashboard.getNumber(key + "PosControl/deadband", 0.5), startMax, threshold);
	}

	public PositionControl(String key, boolean startMax, double threshold) {
		this(	SmartDashboard.getNumber(key + "PosControl/target", 0), key, startMax, threshold);
	}
	
	public boolean isFinished() {
		System.out.println("TEST: " + rate);
		return onTargetCount >= ON_TARGET_GOAL;
	}

	public double getSpeed(double traveled) {
		double error = target - traveled;
		double absError = Math.abs(error);
		if (absError < deadband) {
			speed = 0;
			if(onTargetCount < ON_TARGET_GOAL) onTargetCount++;
		} else if (absError < threshold) {
			speed = (absError - deadband) * slope + minSpeed;
		} else {
			if(startMax) speed = maxSpeed;
			else speed = Math.max(Math.min(speed + rate, maxSpeed), minSpeed);
		}
		return error >= 0 ? speed : -speed;
	}

	public double getTarget() {
		return target;
	}

	public double getMinSpeed() {
		return minSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public double getRate() {
		return rate;
	}

	public double getDeadband() {
		return deadband;
	}
}
