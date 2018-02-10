package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class DigitalInputSystem extends SubsystemBase {
	
	DigitalInput input;
	
	public DigitalInputSystem(int port) {
		this.input = new DigitalInput(port);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void periodic() {
		
	}
	
	public boolean get() {
		return this.input.get();
	}
	
}
