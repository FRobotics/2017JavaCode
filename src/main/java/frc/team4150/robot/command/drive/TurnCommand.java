package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.GyroSystem;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class TurnCommand extends DriveCommand {

	private DriveSystem driveSystem;
	private GyroSystem gyro;

	public TurnCommand(DriveSystem driveSystem, GyroSystem gyro, double degrees) {
		super(degrees, 0.8, true, 55);
		this.gyro = gyro;
		this.driveSystem = driveSystem;
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (isFinished())
			return true;
		double speed = getSpeed(gyro.getAngle());
		driveSystem.setSpeed(-speed, speed, false);
		return false;
	}

	@Override
	public void init() {
		gyro.reset();
	}

}
