package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import main.java.frc.team4150.robot.input.joystick.Axis;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.util.Util;

public class DriveSystem extends SubsystemBase {

	public enum MotorType {
		VICTOR, TALON, JAGUAR
	}

	private PWMSpeedController leftMotor;
	private PWMSpeedController rightMotor;
	
	private double leftMotorSpeed;
	private double rightMotorSpeed;

	public DriveSystem(int leftMotorChannel, int rightMotorChannel, MotorType type) {
		switch (type) {
			case VICTOR:
				leftMotor = new Victor(leftMotorChannel);
				rightMotor = new Victor(rightMotorChannel);
				break;
			case TALON:
				leftMotor = new Talon(leftMotorChannel);
				rightMotor = new Talon(rightMotorChannel);
				break;
			case JAGUAR:
				leftMotor = new Jaguar(leftMotorChannel);
				rightMotor = new Jaguar(rightMotorChannel);
				break;
			default:
				leftMotor = null;
				rightMotor = null;
		}
		
		leftMotorSpeed = 0;
		rightMotorSpeed = 0;
	}

	@Override
	public void init() {
		leftMotor.setExpiration(0.1);
		leftMotor.setSafetyEnabled(true);
		rightMotor.setExpiration(0.1);
		rightMotor.setSafetyEnabled(true);
	}

	/**
	 * Sets the speed of the two motors individually
	 * @param leftMotorSpeed
	 * @param rightMotorSpeed
	 */
	public void setSpeed(double leftMotorSpeed, double rightMotorSpeed) {
		this.leftMotorSpeed = leftMotorSpeed;
		this.rightMotorSpeed = rightMotorSpeed;
	}

	/**
	 * Sets the speed of both motors to 0
	 */
	public void stop() {
		setSpeed(0, 0);
	}
	
	/**
	 * Drives the robot how we want it to. The name might change or I'll add a list of features
	 * @param controller
	 */
	public void drive(ControllerInput controller) {
		
		double fb = controller.getAxis(Axis.RIGHT_X); //Why do these need to be flipped???
		double lr = controller.getAxis(Axis.LEFT_Y);
		
		if(Math.abs(fb) < 0.2) fb = 0;
		if(Math.abs(lr) < 0.2) lr = 0;
		
		//start out with just forward and backward
        double left, right;
        left = right = fb;

        //apply turning v2
        if(Math.abs(lr) > 0.2) {
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

        //apply slow mode
        if(controller.buttonDown(Button.RIGHT_BUMPER)) {
            right /= 2;
            left /= 2;
        }

        leftMotorSpeed = left;
        rightMotorSpeed = right;
        
	}

	@Override
	public void periodic() {
		leftMotor.set(Util.limit(leftMotorSpeed));
		rightMotor.set(Util.limit(rightMotorSpeed));
	}

}
