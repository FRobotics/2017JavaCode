package main.java.frc.team4150.robot.subsystem;

import main.java.frc.team4150.robot.input.joystick.Axis;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.subsystem.motor.MotorSystem;
import main.java.frc.team4150.robot.util.Distance;

public class DriveSystem extends SubsystemBase {
	
	private MotorSystem leftMotor;
	private MotorSystem rightMotor;
	
	private EncoderSystem leftEncoder;
	private EncoderSystem rightEncoder;
	
	private Distance wheelRadius;

	public DriveSystem(MotorSystem leftMotor, MotorSystem rightMotor, EncoderSystem leftEncoder, EncoderSystem rightEncoder, Distance wheelRadius) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.wheelRadius = wheelRadius;
	}

	@Override
	public void init() {
		this.leftMotor.init();
		this.rightMotor.init();
	}

	/**
	 * Sets the speed of the two motors individually
	 * 
	 * @param leftMotorSpeed
	 * @param rightMotorSpeed
	 */
	public void setSpeed(double leftMotorSpeed, double rightMotorSpeed) {
		this.leftMotor.setSpeed(leftMotorSpeed);
		this.rightMotor.setSpeed(rightMotorSpeed);
	}

	/**
	 * Sets the speed of both motors to 0
	 */
	public void stop() {
		setSpeed(0, 0);
	}

	/**
	 * Drives the robot how we want it to
	 * <ul>
	 * <li>the left stick's Y is forward an backward</li>
	 * <li>the right stick's X is left and right</li>
	 * </ul>
	 * 
	 * @param controller
	 */
	public void customDrive(ControllerInput controller) {

		double fb = -controller.getAxis(Axis.LEFT_Y); // inverted so up is forward (positive = forward)
		double lr = -controller.getAxis(Axis.RIGHT_X); // inverted so left turns left & right turns right

		if (Math.abs(fb) < 0.2)
			fb = 0;
		if (Math.abs(lr) < 0.2)
			lr = 0;

		// start out with just forward and backward
		double left, right;
		left = right = fb;

		// apply turning v2
		if (Math.abs(lr) > 0.2) {
			if (fb > 0.0) {
				if (lr > 0.0) {
					left = fb - lr;
					right = Math.max(fb, lr);
				} else {
					left = Math.max(fb, -lr);
					right = fb + lr;
				}
			} else {
				if (lr > 0.0) {
					left = -Math.max(-fb, lr);
					right = fb + lr;
				} else {
					left = fb - lr;
					right = -Math.max(-fb, -lr);
				}
			}
		}

		// apply slow mode
		if (controller.buttonDown(Button.RIGHT_BUMPER)) {
			right /= 2;
			left /= 2;
		}

		leftMotor.setSpeed(left);
		rightMotor.setSpeed(right);

	}

	@Override
	public void periodic() {
		leftMotor.periodic();
		rightMotor.periodic();
	}
	
	public Distance getWheelRadius() {
		return wheelRadius;
	}
	
	public MotorSystem getLeftMotor() {
		return leftMotor;
	}
	
	public MotorSystem getRightMotor() {
		return rightMotor;
	}
	
	public void resetEncoders() {
		this.leftEncoder.resetDistance();
		this.rightEncoder.resetDistance();
	}
	
	public Distance getDistanceTraveled() {
		return new Distance((this.rightEncoder.getDistance().toBaseUnit() + this.leftEncoder.getDistance().toBaseUnit()) / 2);
	}

}
