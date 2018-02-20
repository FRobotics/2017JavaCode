package main.java.frc.team4150.robot.code2017;

import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.base.Command;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.motor.MotorSystem;

public class ClimbCommand extends Command {
	private double speed; 
	private long time; 
	private long startTime; 
	private MotorSystem motor; 
	private DoubleSolenoidSystem brake;  
	public ClimbCommand(MotorSystem motor, DoubleSolenoidSystem brake, double speed, long time) {
		this.speed = speed; 
		this.time = time; 
		this.motor = motor; 
		this.brake = brake; 
	}
	@Override
	public boolean periodic(RobotBase robot) {
		if (System.currentTimeMillis() - startTime > time) {
			motor.stop();
			brake.setDirection(Direction.REVERSE);
			return true;
		}

		return false;
	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis(); 
		motor.setSpeed(speed);
		brake.setDirection(Direction.FORWARD);
	}
	
}
