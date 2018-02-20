package main.java.frc.team4150.robot.command;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.SolenoidSystem;

public class SetSolenoidCommand extends Command {
	private long startTime;
	private long wait;
	private SolenoidSystem solenoid;
	private boolean direction;
	
	public SetSolenoidCommand (SolenoidSystem solenoid, boolean direction, long wait) {
		this.solenoid = solenoid;
		this.direction = direction;
		this.wait = wait;
	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
		solenoid.set(direction);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (System.currentTimeMillis() - startTime > wait) {
			return true;
		}
		return false;
	}
}
