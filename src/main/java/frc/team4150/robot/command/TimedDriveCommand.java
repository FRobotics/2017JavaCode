package main.java.frc.team4150.robot.command;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class TimedDriveCommand extends Command {
	private long startTime;
	private long wait;
	private DriveSystem motor;
	private double speed;
	
	public TimedDriveCommand (DriveSystem motor, double speed, long wait) {
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
		motor.setSpeed(speed, speed);
		return false;
	}
}
