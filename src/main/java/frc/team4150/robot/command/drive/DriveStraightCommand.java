package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.util.equation.SpeedDistanceTimeEquation;

public class DriveStraightCommand extends DriveCommand {

	public DriveStraightCommand(DriveSystem driveSystem, SpeedDistanceTimeEquation equation) {
		super(driveSystem, equation);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		getDriveSystem().setSpeed(getEquation().get(SpeedDistanceTimeEquation.Name.SPEED),
				getEquation().get(SpeedDistanceTimeEquation.Name.SPEED));
		if (System.currentTimeMillis() - getStartTime() > getEquation().get(SpeedDistanceTimeEquation.Name.TIME)) {
			getDriveSystem().stop();
			return true;
		}
		return false;
	}
	
}
