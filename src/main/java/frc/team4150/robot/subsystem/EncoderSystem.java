package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Encoder;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.util.Distance;

public class EncoderSystem extends SubsystemBase {
	
	private Encoder encoder;
	
	public EncoderSystem(int port1, int port2, Distance radius, boolean invert) {
		encoder = new Encoder(port1, port2, invert, Encoder.EncodingType.k4X);
		encoder.setDistancePerPulse(radius.to(Distance.Unit.INCHES) * Distance.inchPerDegree);
	}
	
	public EncoderSystem(int port1, int port2, Distance radius) {
		this(port1, port2, radius, false);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void periodic() {
		
	}
	
	public Distance getDistance() {
		return new Distance(encoder.getDistance(), Distance.Unit.INCHES);
	}
	
	public int getCount() {
		return encoder.get();
	}
	
}
