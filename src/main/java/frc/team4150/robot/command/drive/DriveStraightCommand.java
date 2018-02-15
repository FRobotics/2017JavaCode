package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.util.Distance;

public class DriveStraightCommand extends DriveCommand {

	public DriveStraightCommand(DriveSystem driveSystem, Distance distance) {
		super(driveSystem, distance);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if(isFinished()) return true;
		getDriveSystem().setSpeed(getSpeed(), getSpeed());
		return false;
	}

}
