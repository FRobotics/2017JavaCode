package main.java.frc.team4150.robot.command;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.base.Command;

public class WaitCommand extends Command {
	
	private long startTime;
	private long wait;
	
	public WaitCommand (long wait) {
		this.wait = wait;
	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (System.currentTimeMillis() - startTime > wait) {
			return true;
		}
		return false;
	}
	
}
