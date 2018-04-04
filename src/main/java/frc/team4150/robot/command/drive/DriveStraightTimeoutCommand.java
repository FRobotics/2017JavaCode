package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class DriveStraightTimeoutCommand extends DriveStraightCommand {
	
	private long timeout;
	private long startTime;
	
	public DriveStraightTimeoutCommand(DriveSystem driveSystem, double distance, long timeout, boolean highShift) {
		super(driveSystem, distance, highShift);
		this.timeout = timeout;
	}
	
	public DriveStraightTimeoutCommand(DriveSystem driveSystem, double distance, long timeout) {
		this(driveSystem, distance, timeout, false);
	}
	
	@Override
	public void init() {
		super.init();
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public boolean periodic(RobotBase robot) {
		if (System.currentTimeMillis() - startTime > timeout) {
			getDriveSystem().stop();
			return true;
		}
		return super.periodic(robot);
	}

}
