package main.java.frc.team4150.robot.code2018;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.SetSolenoidCommand;
import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.command.drive.TurnCommand;
import main.java.frc.team4150.robot.input.joystick.Axis;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.SolenoidSystem;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.drive.QuadDriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.ShifterSystem;
import main.java.frc.team4150.robot.subsystem.motor.LimitedMotorSystem;
import main.java.frc.team4150.robot.util.Util;

public class Robot extends RobotBase {

	public static final double WHEEL_RADIUS = 3 * 12;

	public Robot() {
		super(Subsystem.values(), Input.values());
	}

	@Override
	public void start() {

	}

	@Override
	public void addCommands() {
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();

		SolenoidSystem arm = (SolenoidSystem) Subsystem.ARM.getSubsystem();

		String[] commandStrings = SmartDashboard.getStringArray("commands", new String[] {});
		for (String commandString : commandStrings) {
			String[] parts = commandString.split(":");
			String command = parts[0];
			switch (command) {
				case "driveStraight": {
					double distance = Double.parseDouble(parts[1]);
					this.addCommand(new DriveStraightCommand(drive, distance));
					break;
				}
				case "turn": {
					double degrees = Double.parseDouble(parts[1]);
					boolean turnLeft = Boolean.parseBoolean(parts[2]);
					this.addCommand(new TurnCommand(drive, Util.fromDegrees(degrees, drive.getWheelRadius()),
													turnLeft));
					break;
				}
				case "setArm": {
					boolean direction = Boolean.parseBoolean(parts[1]);
					double wait = Double.parseDouble(parts[2]);
					this.addCommand(new SetSolenoidCommand(arm, direction, (long) (wait * 1000)));
					break;
				}
			}
		}
	}

	@Override
	public void teleopStart() {

	}

	@Override
	public void teleopLoop() {

		ControllerInput controller = (ControllerInput) Input.CONTROLLER_MOVEMENT.getInput();
		ControllerInput controller2 = (ControllerInput) Input.CONTROLLER_ACTIONS.getInput();

		QuadDriveSystem drive = (QuadDriveSystem) Subsystem.DRIVE.getSubsystem();
		EncoderSystem leftEncoder = drive.getLeftEncoder();
		EncoderSystem rightEncoder = drive.getRightEncoder();
		ShifterSystem shifter = (ShifterSystem) Subsystem.SHIFTER.getSubsystem();

		LimitedMotorSystem elevator = (LimitedMotorSystem) Subsystem.ELEVATOR.getSubsystem();
		DoubleSolenoidSystem elevatorBrake = (DoubleSolenoidSystem) Subsystem.ELEVATOR_BRAKE.getSubsystem();
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();

		drive.customDrive(controller, false);

		if (controller.buttonPressed(Button.A)) {
			shifter.setShift(true);
		} else if (controller.buttonPressed(Button.B)) {
			shifter.setShift(false);
		}

		if (controller2.buttonDown(Button.START)) {
			elevator.setSpeed(0.75);
		} else if (controller2.buttonDown(Button.BACK)) {
			elevator.setSpeed(-1.0);
		} else {
			elevator.stop();
		}

		if (controller2.getAxis(Axis.TRIGGER_LEFT) > 0.5) {
			arm.setDirection(Direction.REVERSE);
		} else if (controller2.getAxis(Axis.TRIGGER_RIGHT) > 0.5) {
			arm.setDirection(Direction.FORWARD);
		}

		if (controller2.buttonPressed(Button.A)) {
			elevatorBrake.setDirection(Direction.REVERSE);
		} else if (controller2.buttonPressed(Button.B)) {
			elevatorBrake.setDirection(Direction.FORWARD);
		}

		SmartDashboard.putNumber("leftMotor", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("rightMotor", drive.getRightMotor().getSpeed());
		SmartDashboard.putNumber("leftEncoder", leftEncoder.getDistance());
		SmartDashboard.putNumber("rightEncoder", rightEncoder.getDistance());
	}

	@Override
	public void stopLoop() {
	}

	@Override
	public void updateNTVariables() {
		// TODO Auto-generated method stub
		
	}
}
