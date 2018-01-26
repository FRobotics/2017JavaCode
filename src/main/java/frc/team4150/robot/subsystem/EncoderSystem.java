package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Encoder;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class EncoderSystem extends SubsystemBase {
	
	private Encoder encoder;
	
	public EncoderSystem(int port1, int port2, boolean invert) {
		encoder = new Encoder(port1, port2, invert, Encoder.EncodingType.k4X);
	}
	
	public EncoderSystem(int port1, int port2) {
		encoder = new Encoder(port1, port2, false, Encoder.EncodingType.k4X);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void periodic() {
		
	}
	
	public double getDistance() {
		return encoder.getDistance();
	}
	
}
