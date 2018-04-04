package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class DriveStraightCommand extends DriveCommand {

	private DriveSystem driveSystem;
	private boolean highShift;
	
	public DriveStraightCommand(DriveSystem driveSystem, double threshold, double distance, boolean highShift) {
		super(distance, 1.0, false, threshold);
		this.driveSystem = driveSystem;
		this.highShift = highShift;
	}
	
	public DriveStraightCommand(DriveSystem driveSystem, double distance, boolean highShift) {
		this(driveSystem, 24, distance, highShift);
	}
	
	public DriveStraightCommand(DriveSystem driveSystem, double distance) {
		this(driveSystem, 24, distance, false);
	}
	
	@Override
	public void init() {
		driveSystem.resetEncoders();
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if(isFinished()) {
			driveSystem.stop();
			return true;
		}
		double speed = getSpeed(driveSystem.getDistanceTraveled());
		driveSystem.setSpeed(speed, speed, highShift);
		return false;
	}
	
	public DriveSystem getDriveSystem() {
		return driveSystem;
	}

}
