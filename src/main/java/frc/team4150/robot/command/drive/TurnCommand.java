package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.subsystem.GyroSystem;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;

public class TurnCommand extends DriveCommand {

	private DriveSystem driveSystem;
	private GyroSystem gyro;
	private boolean turnLeft;

	public TurnCommand(DriveSystem driveSystem, GyroSystem gyro, double degrees, boolean turnLeft) {
		super(degrees, true);
		this.gyro = gyro;
		this.driveSystem = driveSystem;
		this.turnLeft = turnLeft;
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (isFinished(gyro.getAngle()))
			return true;
		double speed = getSpeed(gyro.getAngle());
		if (turnLeft)
			driveSystem.setSpeed(speed, -speed);
		else
			driveSystem.setSpeed(-speed, speed);
		return false;
	}

	@Override
	public void init() {
		gyro.reset();
	}

}
