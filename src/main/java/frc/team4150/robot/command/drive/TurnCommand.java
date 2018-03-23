package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class TurnCommand extends DriveCommand {

	private boolean turnLeft;

	public TurnCommand(DriveSystem driveSystem, double distance, boolean turnLeft) {
		super(driveSystem, (distance / 360) * 24 * Math.PI);
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
