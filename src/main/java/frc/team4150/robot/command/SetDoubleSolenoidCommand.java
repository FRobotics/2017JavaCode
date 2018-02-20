package main.java.frc.team4150.robot.command;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;

public class SetDoubleSolenoidCommand extends Command {
	
	private long startTime;
	private long wait;
	private DoubleSolenoidSystem solenoid;
	private Direction direction;
	
	public SetDoubleSolenoidCommand (DoubleSolenoidSystem solenoid, Direction direction, long wait) {
		this.solenoid = solenoid;
		this.direction = direction;
		this.wait = wait;
	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
		solenoid.setDirection(direction);
	}

	@Override
	public boolean periodic(RobotBase robot) {
		if (System.currentTimeMillis() - startTime > wait) {
			return true;
		}
		return false;
	}
	
}
