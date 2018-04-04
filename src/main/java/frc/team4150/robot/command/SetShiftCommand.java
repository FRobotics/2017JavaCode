package main.java.frc.team4150.robot.command;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.drive.ShifterSystem;

public class SetShiftCommand extends Command {
	private long startTime;
	private long wait;
	private ShifterSystem shifter;
	private boolean high;
	
	public SetShiftCommand (ShifterSystem shifter, boolean high, long wait) {
		this.shifter = shifter;
		this.high = high;
		this.wait = wait;
	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
		shifter.setShift(high);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (System.currentTimeMillis() - startTime > wait) {
			return true;
		}
		return false;
	}
}
