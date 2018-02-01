package main.java.frc.team4150.robot.Robot2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.command.SetSolenoidCommand;
import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.command.drive.TurnCommand;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.subsystem.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.SparkSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Distance.Unit;
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

		drive.customDrive(controller);
		
		SmartDashboard.putNumber("leftEncoder", leftEncoder.getDistance().to(Unit.FEET));
		SmartDashboard.putNumber("rightEncoder", rightEncoder.getDistance().to(Unit.FEET));

		// TODO: turn these into commands (don't use these)

		if (controller2.buttonPressed(Button.X))
			gear_arms.flipDirection();

		if (controller2.buttonPressed(Button.Y))
			gear_platform.flipDirection();

		if (controller2.buttonDown(Button.B))
			climbMotor.setSpeed(1.0);
		else
			climbMotor.setSpeed(0.0);

		// TODO: deal with other subsystems based on input
	}
}
