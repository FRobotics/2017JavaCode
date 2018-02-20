package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class DriveStraightCommand extends DriveCommand {

	public DriveStraightCommand(DriveSystem driveSystem, double distance) {
		super(driveSystem, distance);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if(isFinished()) {
			getDriveSystem().setSpeed(0, 0);
			return true;
		}
		getDriveSystem().setSpeed(getSpeed(), getSpeed());
		return false;
	}

}
