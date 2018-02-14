package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Time;

public class TurnCommand extends DriveCommand {

	public TurnCommand(DriveSystem driveSystem, Distance distance) {
		super(driveSystem, distance);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		/*getDriveSystem().setSpeed(getSpeed() / 2, -getSpeed() / 2);
		if (System.currentTimeMillis() - getStartTime() > getTime().to(Time.Unit.MILLIS)) {
			getDriveSystem().stop();
			return true;
		}
		return false;*/
		return true; //UNFINISHED
	}

}
