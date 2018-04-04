package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.util.PositionControl;

public abstract class DriveCommand extends Command {

	private PositionControl posControl;
	
	public DriveCommand(double distance, double maxSpeed, boolean startMax, double threshold) {
		this.posControl = new PositionControl(distance, 0.2, maxSpeed, 0.05, 2, startMax, threshold);
	}

	public boolean isFinished() {
		return posControl.isFinished();
	}

	public double getSpeed(double measure) {
		return posControl.getSpeed(measure);
	}
	
	public PositionControl getPosControl() {
		return this.posControl;
	}
}
