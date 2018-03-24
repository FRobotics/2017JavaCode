package main.java.frc.team4150.robot.code2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.motor.types.SparkSystem;

public class Robot extends main.java.frc.team4150.robot.RobotBase {

	public static final double WHEEL_RADIUS = 3 * 12;

	public Robot() {
		super(Subsystem.values(), Input.values());
	}

	@Override
	public void start() {

	}

	@Override
	public void addCommands() {
		// /!\ 2017 /!\
		/*DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		DoubleSolenoidSystem gear_platform = (DoubleSolenoidSystem) Subsystem.GEAR_PLATFORM.getSubsystem();
		DoubleSolenoidSystem gear_arms = (DoubleSolenoidSystem) Subsystem.GEAR_ARMS.getSubsystem();
		this.addCommand(new DriveStraightCommand(drive, 30));
		this.addCommand(new TurnCommand(drive, 30, false));
		this.addCommand(new SetDoubleSolenoidCommand(gear_platform, Direction.FORWARD, 1000));
		this.addCommand(new SetDoubleSolenoidCommand(gear_arms, Direction.FORWARD, 1000));*/
	}

	@Override
	public void teleopStart() {

	}

	@Override
	public void teleopLoop() {
		System.out.println("teleop periodic start");

		ControllerInput controller = (ControllerInput) Input.CONTROLLER_MOVEMENT.getInput();
		ControllerInput controller2 = (ControllerInput) Input.CONTROLLER_ACTIONS.getInput();

		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		EncoderSystem leftEncoder = drive.getLeftEncoder();
		EncoderSystem rightEncoder = drive.getRightEncoder();
		DoubleSolenoidSystem gear_arms = (DoubleSolenoidSystem) Subsystem.GEAR_ARMS.getSubsystem();
		DoubleSolenoidSystem gear_platform = (DoubleSolenoidSystem) Subsystem.GEAR_PLATFORM.getSubsystem();
		SparkSystem climbMotor = (SparkSystem) Subsystem.CLIMB_MOTOR.getSubsystem();
		DoubleSolenoidSystem climbBrake = (DoubleSolenoidSystem) Subsystem.CLIMB_BRAKE.getSubsystem();

		drive.customDrive(controller);

		SmartDashboard.putNumber("leftMotor", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("rightMotor", drive.getRightMotor().getSpeed());
		SmartDashboard.putNumber("leftEncoder", leftEncoder.getDistance());
		SmartDashboard.putNumber("rightEncoder", rightEncoder.getDistance());

		// TODO: turn these into commands (don't use these)

		if (controller2.buttonPressed(Button.X))
			gear_arms.flipDirection();

		if (controller2.buttonPressed(Button.Y))
			gear_platform.flipDirection();

		if (controller2.buttonDown(Button.START) || controller2.buttonDown(Button.BACK)) {
			climbBrake.setDirection(Direction.FORWARD);
		} else {
			climbBrake.setDirection(Direction.REVERSE);
		}
		
		if (controller2.buttonDown(Button.START) && !controller2.buttonDown(Button.BACK)) {
			climbMotor.setSpeed(1);
		}
		if (controller2.buttonDown(Button.BACK) && !controller2.buttonDown(Button.START)) {
			climbMotor.setSpeed(-1);
		}

		// TODO: deal with other subsystems based on input
	}

	@Override
	public void stopLoop() {
	}

	@Override
	public void updateNTVariables() {
	}
}
