package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Time;

public abstract class DriveCommand extends Command {

	private DriveSystem driveSystem;
	private Distance distance;
	private Time time;
	private long startTime;

	public DriveCommand(DriveSystem driveSystem, Distance distance, Time time) {
		this.driveSystem = driveSystem;
		this.distance = distance;
		this.time = time;
	}
	
	public DriveSystem getDriveSystem() {
		return driveSystem;
	}
	
	public Distance getDistance() {
		return distance;
	}
	
	public Time getTime() {
		return time;
	}
	
	public double getSpeed() {
		return getDistance().toDegrees(driveSystem.getWheelRadius()) / getTime().toBaseUnit();
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	@Override
	public void init() {
		startTime = System.currentTimeMillis();
	}
}
