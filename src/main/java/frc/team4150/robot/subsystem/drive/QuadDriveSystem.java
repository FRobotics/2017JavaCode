package main.java.frc.team4150.robot.subsystem.drive;

import main.java.frc.team4150.robot.subsystem.motor.MotorSystem;

public class QuadDriveSystem extends DriveSystem {

	private MotorSystem leftMotor2;
	private MotorSystem rightMotor2;
	
	public QuadDriveSystem(MotorSystem leftMotor, MotorSystem leftMotor2, MotorSystem rightMotor, MotorSystem rightMotor2, EncoderSystem leftEncoder,
			EncoderSystem rightEncoder, double wheelRadius) {
		super(leftMotor, rightMotor, leftEncoder, rightEncoder, wheelRadius);
		this.leftMotor2 = leftMotor2;
		this.rightMotor2 = rightMotor2;
	}
	
	public MotorSystem getLeftMotor2() {
		return leftMotor2;
	}
	
	public MotorSystem getRightMotor2() {
		return rightMotor2;
	}
	
	@Override
	public void init() {
		super.init();
		rightMotor2.init();
		leftMotor2.init();
	}
	
	@Override
	public void periodic() {
		super.periodic();
		leftMotor2.periodic();
		rightMotor2.periodic();
	}
	
	@Override
	public void setSpeed(double leftMotorSpeed, double rightMotorSpeed) {
		double left = leftMotorSpeed;
		double right = rightMotorSpeed;
		super.setSpeed(left, right);
		leftMotor2.setSpeed(left);
		rightMotor2.setSpeed(-right);
	}

}
