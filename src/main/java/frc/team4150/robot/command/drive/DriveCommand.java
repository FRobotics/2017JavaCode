package main.java.frc.team4150.robot.command.drive;

import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.util.PositionControl;

public abstract class DriveCommand extends Command {

	private PositionControl posControl;

	public DriveCommand(double distance, boolean startMax) {
		this.posControl = new PositionControl(distance, "drive", startMax);
	}

	public boolean isFinished(double measure) {
		return posControl.onTarget(measure); //HACK
	}

	public double getSpeed(double measure) {
		return posControl.getSpeed(measure);
	}
	
	public PositionControl getPosControl() {
		return this.posControl;
	}
}
