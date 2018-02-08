package main.java.frc.team4150.robot.subsystem.motor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonSRXSystem extends MotorSystem {

	private WPI_TalonSRX motor;
	
	public TalonSRXSystem(int deviceNumber) {
		motor = new WPI_TalonSRX(deviceNumber);
	}

	@Override
	public void init() {
		motor.setExpiration(0.1);
		motor.setSafetyEnabled(true);
	}

	@Override
	public void periodic() {
		motor.set(getSpeed());
	}
	
}
