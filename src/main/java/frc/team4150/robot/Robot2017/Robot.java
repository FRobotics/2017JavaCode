package main.java.frc.team4150.robot.Robot2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.command.SetSolenoidCommand;
import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.command.drive.TurnCommand;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.motor.SparkSystem;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.subsystem.EncoderSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Distance.Unit;
import main.java.frc.team4150.robot.util.PositionControl;
import main.java.frc.team4150.robot.util.Time;

public class Robot extends main.java.frc.team4150.robot.RobotBase {

	public static final Distance WHEEL_RADIUS = new Distance(3, Distance.Unit.INCHES);

	public Robot() {
		super(Subsystem.values(), Input.values());
	}

	@Override
	public void start() {

	}

	@Override
	public void addCommands() {
		// /!\ 2017 /!\
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		DoubleSolenoidSystem gear_platform = (DoubleSolenoidSystem) Subsystem.GEAR_PLATFORM.getSubsystem();
		DoubleSolenoidSystem gear_arms = (DoubleSolenoidSystem) Subsystem.GEAR_ARMS.getSubsystem();
		this.addCommand(new DriveStraightCommand(drive, new Distance(30, Distance.Unit.INCHES), new Time(1, Time.Unit.SEC)));
		this.addCommand(new TurnCommand(drive, new Distance(30, Distance.Unit.INCHES), new Time(1, Time.Unit.SEC)));
		this.addCommand(new SetSolenoidCommand(gear_platform, Direction.FORWARD, new Time(1, Time.Unit.SEC)));
		this.addCommand(new SetSolenoidCommand(gear_arms, Direction.FORWARD, new Time(1, Time.Unit.SEC)));
	}

	@Override
	public void teleopStart() {

	}

	@Override
	public void teleopLoop() {
		System.out.println("teleop periodic start");

		ControllerInput controller = (ControllerInput) Input.CONTROLLER_MOVEMENT.getInput();
		ControllerInput controller2 = (ControllerInput) Input.CONTROLLER_ACTIONS.getInput();

		EncoderSystem leftEncoder = (EncoderSystem) Subsystem.LEFT_ENCODER.getSubsystem();
		EncoderSystem rightEncoder = (EncoderSystem) Subsystem.RIGHT_ENCODER.getSubsystem();

		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		DoubleSolenoidSystem gear_arms = (DoubleSolenoidSystem) Subsystem.GEAR_ARMS.getSubsystem();
		DoubleSolenoidSystem gear_platform = (DoubleSolenoidSystem) Subsystem.GEAR_PLATFORM.getSubsystem();
		SparkSystem climbMotor = (SparkSystem) Subsystem.CLIMB_MOTOR.getSubsystem();
		DoubleSolenoidSystem climbBrake = (DoubleSolenoidSystem) Subsystem.CLIMB_BRAKE.getSubsystem();

		drive.customDrive(controller);

		SmartDashboard.putNumber("leftMotor", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("rightMotor", drive.getRightMotor().getSpeed());
		SmartDashboard.putNumber("leftEncoder", leftEncoder.getDistance().to(Unit.FEET));
		SmartDashboard.putNumber("rightEncoder", rightEncoder.getDistance().to(Unit.FEET));

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
	
	public PositionControl createDriveControl(Distance distance) {
		Distance target = new Distance(SmartDashboard.getNumber("drivePosControlTarget", distance.to(Unit.FEET)), Unit.FEET);
		double minSpeed = SmartDashboard.getNumber("drivePosControlMinSpeed", 0);
		double maxSpeed = SmartDashboard.getNumber("drivePosControlMaxSpeed", 0);
		double rate = SmartDashboard.getNumber("drivePosControlRate", 0);
		double deadband = SmartDashboard.getNumber("drivePosControlDeadband", 0);
		return new PositionControl(target, minSpeed, maxSpeed, rate, deadband);
	}
}
