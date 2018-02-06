package main.java.frc.team4150.robot.subsystem.motor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXSystem extends MotorSystem {

	private TalonSRX motor;
	
	public TalonSRXSystem(int port) {
		motor = new TalonSRX(port);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void periodic() {
		motor.set(ControlMode.Current, getSpeed());
	}
	
}
