package main.java.frc.team4150.robot.subsystem;

import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.util.ADIS16448_IMU;

public class GyroSystem extends SubsystemBase {
	
	private ADIS16448_IMU gyro;
	
	public GyroSystem() {
		this.gyro = new ADIS16448_IMU();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void periodic() {
		
	}
	
	public double getAngle() {
		return gyro.getAngleZ();
	}
	
	public void reset() {
		gyro.reset();
	}

}
