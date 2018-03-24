package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class DriveStraightCommand extends DriveCommand {

	private DriveSystem driveSystem;
	
	public DriveStraightCommand(DriveSystem driveSystem, double distance) {
		super(distance, false);
		this.driveSystem = driveSystem;
	}
	
	@Override
	public void init() {
		driveSystem.resetEncoders();
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if(isFinished(driveSystem.getDistanceTraveled())) {
			driveSystem.setSpeed(0, 0);
			return true;
		}
		double speed = getSpeed(driveSystem.getDistanceTraveled());
		driveSystem.setSpeed(speed, speed);
		return false;
	}

}
