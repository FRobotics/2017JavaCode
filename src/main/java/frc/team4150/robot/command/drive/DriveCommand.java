package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.util.equation.SpeedDistanceTimeEquation;

public abstract class DriveCommand extends Command {

	private DriveSystem driveSystem;
	private SpeedDistanceTimeEquation equation;
	private long startTime;

	public DriveCommand(DriveSystem driveSystem, SpeedDistanceTimeEquation equation) {
		this.driveSystem = driveSystem;
		this.equation = equation;
		this.startTime = System.currentTimeMillis();
	}
	
	public DriveSystem getDriveSystem() {
		return driveSystem;
	}
	
	public SpeedDistanceTimeEquation getEquation() {
		return equation;
	}
	
	public long getStartTime() {
		return startTime;
	}
}
