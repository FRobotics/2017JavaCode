package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Time;
import main.java.frc.team4150.robot.util.Time.Unit;

public class DriveStraightCommand extends DriveCommand {

	public DriveStraightCommand(DriveSystem driveSystem, Distance distance, Time time) {
		super(driveSystem, distance, time);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		System.out.println(getTime().to(Unit.MILLIS) + "::" + getSpeed());
		getDriveSystem().setSpeed(getSpeed(), getSpeed());
		if (System.currentTimeMillis() - getStartTime() > getTime().to(Unit.MILLIS)) {
			getDriveSystem().stop();
			return true;
		}
		return false;
	}

}
