package main.java.frc.team4150.robot.subsystem.drive;

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

	public DriveSystem(MotorSystem leftMotor, MotorSystem rightMotor, EncoderSystem leftEncoder,
			EncoderSystem rightEncoder, Distance wheelRadius) {
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
		this.rightMotor.setSpeed(-rightMotorSpeed);
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
		customDrive(controller, false);
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
	public void customDrive(ControllerInput controller, boolean invert) {

		double fb = smooth(controller.getAxis(Axis.LEFT_Y), 0.2, 2); // inverted so up is forward (positive = forward)
		double lr = smooth(controller.getAxis(Axis.RIGHT_X), 0.2, 3); // inverted so left turns left & right turns right

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
			left *= 0.75;
			right *= 0.75;
		}

		// apply slow mode
		if (controller.buttonDown(Button.RIGHT_BUMPER)) {
			right *= 0.5;
			left *= 0.5;
		}

		if (invert) {
			left *= -1;
			right *= -1;
		}

		setSpeed(left, right);

	}

	public static double smooth(double value, double deadband, double power) {
		if (value > deadband) {
			double newValue = (value - deadband) / (1 - deadband);
			return pow(newValue, power);
		}
		if (value < -deadband) {
			double newValue = (value + deadband) / (1 - deadband);
			return -Math.abs(pow(newValue, power));
		}
		return 0;
	}
	
	public static double pow(double value, double power) {
		double newValue = 1;
		for(int i = 0; i < power; i++) {
			newValue *= value;
		}
		return newValue;
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

	public EncoderSystem getLeftEncoder() {
		return leftEncoder;
	}

	public EncoderSystem getRightEncoder() {
		return rightEncoder;
	}

	public void resetEncoders() {
		this.leftEncoder.resetDistance();
		this.rightEncoder.resetDistance();
	}

	public Distance getDistanceTraveled() {
		return new Distance((this.rightEncoder.getDistance().toBaseUnit() + this.leftEncoder.getDistance().toBaseUnit())
				/ 2);
	}

}
