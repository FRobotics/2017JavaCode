package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.util.PositionControl;

public abstract class DriveCommand extends Command {

	private DriveSystem driveSystem;
	private PositionControl posControl;

	public DriveCommand(DriveSystem driveSystem, double distance) {
		this.driveSystem = driveSystem;
		this.posControl = new PositionControl(distance, "drive", driveSystem.getWheelRadius());
	}
	
	public DriveSystem getDriveSystem() {
		return driveSystem;
	}
	
	public boolean isFinished() {
		return posControl.onTarget(driveSystem.getDistanceTraveled());
	}
	
	public double getSpeed() {
		return posControl.getSpeed(driveSystem.getDistanceTraveled());
	}
	
	@Override
	public void init() {
		driveSystem.resetEncoders();	}
}
