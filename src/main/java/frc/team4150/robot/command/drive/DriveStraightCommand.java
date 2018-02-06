package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Time;
import main.java.frc.team4150.robot.util.Time.Unit;

public class DriveStraightCommand extends DriveCommand {

	public DriveStraightCommand(DriveSystem driveSystem, Distance distance) {
		super(driveSystem, distance);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		getDriveSystem().setSpeed(getSpeed(), getSpeed());
		if (getDriveSystem()) {
			return true;
		}
		return false;
	}

}
