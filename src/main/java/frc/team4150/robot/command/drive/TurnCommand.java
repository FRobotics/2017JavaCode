package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.util.Distance;

public class TurnCommand extends DriveCommand {

	private boolean turnLeft;

	public TurnCommand(DriveSystem driveSystem, Distance distance, boolean turnLeft) {
		super(driveSystem, distance);
		this.turnLeft = turnLeft;
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (isFinished())
			return true;
		if (turnLeft)
			getDriveSystem().setSpeed(-getSpeed(), getSpeed());
		else
			getDriveSystem().setSpeed(getSpeed(), -getSpeed());
		return false;
	}

}
