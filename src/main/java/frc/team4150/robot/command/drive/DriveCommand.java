package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.PositionControl;
import main.java.frc.team4150.robot.util.Time;

public abstract class DriveCommand extends Command {

	private DriveSystem driveSystem;
	private PositionControl posControl;
	private long startTime;

	public DriveCommand(DriveSystem driveSystem, Distance distance) {
		this.driveSystem = driveSystem;
		this.posControl = new PositionControl(distance, "drive");
	}
	
	public DriveSystem getDriveSystem() {
		return driveSystem;
	}
	
	public double getSpeed() {
		return posControl.getSpeed(driveSystem.getDistanceTraveled(), driveSystem.getWheelRadius());
	}
	
	@Override
	public void init() {}
}
