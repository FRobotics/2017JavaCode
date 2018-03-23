package main.java.frc.team4150.robot.command;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.motor.LimitedMotorSystem;

public class TimedMotorCommand extends Command {
	private long startTime;
	private long wait;
	private LimitedMotorSystem motor;
	private double speed;
	
	public TimedMotorCommand (LimitedMotorSystem motor, double speed, long wait) {
		this.motor = motor;
		this.speed = speed;
		this.wait = wait;
	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (System.currentTimeMillis() - startTime > wait) {
			motor.stop();
			return true;
		}
		motor.setSpeed(speed);
		return false;
	}
}