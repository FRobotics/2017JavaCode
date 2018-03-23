package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class DigitalInputSystem extends SubsystemBase {
	
	private DigitalInput input;
	private boolean triggered;
	private boolean inverted;
	
	public DigitalInputSystem(int port) {
		this(port, false);
	}
	
	public DigitalInputSystem(int port, boolean inverted) {
		this.input = new DigitalInput(port);
		this.triggered = false;
		this.inverted = inverted;
	}

	@Override
	public void init() {
		
	}
	
	public boolean get() {
		boolean rawInput = this.input.get();
		boolean input = inverted ? rawInput : !rawInput;
		return input;
	}
	
	public boolean triggered() {
		return triggered;
	}
	
	public void reset() {
		this.triggered = false;
	}

	@Override
	public void periodic() {
		boolean rawInput = this.input.get();
		boolean input = inverted ? rawInput : !rawInput;
		if(input) triggered = true;
	}
	
}
