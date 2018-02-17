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
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Distance.Unit;
import main.java.frc.team4150.robot.util.Time;

public class Robot extends RobotBase {

	public static boolean tankDrive = false;
	public static final Distance WHEEL_RADIUS = new Distance(3, Distance.Unit.INCHES);

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
					this.addCommand(new DriveStraightCommand(drive, new Distance(distance, Unit.FEET)));
					break;
				}
				case "turn": {
					double degrees = Double.parseDouble(parts[1]);
					boolean turnLeft = Boolean.parseBoolean(parts[2]);
					this.addCommand(new TurnCommand(drive, Distance.fromDegrees(degrees, drive.getWheelRadius()),
													turnLeft));
					System.out.println("uh this isn't finished ahahah");
					break;
				}
				case "setArm": {
					boolean direction = Boolean.parseBoolean(parts[1]);
					double wait = Double.parseDouble(parts[2]);
					this.addCommand(new SetSolenoidCommand(	arm, direction,
															new Time((int) (wait * 1000), Time.Unit.MILLIS)));
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

		if(controller.buttonPressed(Button.RIGHT_BUMPER)) {
			tankDrive = !tankDrive;
		}
		
		if(tankDrive) {
			double leftSpeed = controller.getAxis(Axis.LEFT_Y);
			double rightSpeed = controller.getAxis(Axis.RIGHT_Y);
			drive.getLeftMotor().setSpeed(leftSpeed);
			drive.getLeftMotor2().setSpeed(leftSpeed);
			drive.getRightMotor().setSpeed(rightSpeed);
			drive.getRightMotor2().setSpeed(rightSpeed);
		}else {
			drive.customDrive(controller, true);
		}
		
		if (controller.buttonPressed(Button.A)) {
			shifter.setShift(true);
		} else if (controller.buttonPressed(Button.B)) {
			shifter.setShift(false);
		}

		if (controller2.buttonDown(Button.START)) {
			System.out.println("start");
			elevator.setSpeed(0.5);
		} else if (controller2.buttonDown(Button.BACK)) {
			elevator.setSpeed(-0.5);
		} else {
			elevator.stop();
		}

		if (controller2.buttonPressed(Button.X)) {
			arm.setDirection(Direction.REVERSE);
		} else if (controller2.buttonPressed(Button.Y)) {
			arm.setDirection(Direction.FORWARD);
		}

		if (controller2.buttonPressed(Button.A)) {
			elevatorBrake.setDirection(Direction.REVERSE);
		} else if (controller2.buttonPressed(Button.B)) {
			elevatorBrake.setDirection(Direction.FORWARD);
		}

		SmartDashboard.putNumber("leftMotor", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("rightMotor", drive.getRightMotor().getSpeed());
		SmartDashboard.putNumber("leftEncoder", leftEncoder.getDistance().to(Unit.FEET));
		SmartDashboard.putNumber("rightEncoder", rightEncoder.getDistance().to(Unit.FEET));
	}
}
