package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class DigitalInputSystem extends SubsystemBase {
	
	private DigitalInput input;
	private boolean triggered;
	private boolean pulsing;
	private boolean inverted;
	
	public DigitalInputSystem(int port) {
		this(port, false);
	}
	
	public DigitalInputSystem(int port, boolean inverted) {
		this.input = new DigitalInput(port);
		this.triggered = false;
		this.pulsing = false;
		this.inverted = inverted;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void periodic() {
		if(this.get()) {
			if(!pulsing) triggered = !triggered;
			pulsing = true;
		}else {
			pulsing = false;
		}
	}
	
	public boolean get() {
		boolean input = this.input.get();
		return inverted ? input : !input;
	}
	
	public boolean triggered() {
		return triggered;
	}
	
	public void reset() {
		this.triggered = false;
	}
	
}
