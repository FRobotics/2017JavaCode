package main.java.frc.team4150.robot.Robot2017;

import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.subsystem.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.SparkSystem;
import main.java.frc.team4150.robot.util.equation.SpeedDistanceTimeEquation;
import main.java.frc.team4150.robot.util.equation.SpeedDistanceTimeVariable;

public class Robot extends main.java.frc.team4150.robot.RobotBase {

	public Robot() {
		super(Subsystem.values(), Input.values());
	}

	@Override
	public void start() {

	}

	@Override
	public void addCommands() {
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		this.addCommand(new DriveStraightCommand(drive,
				new SpeedDistanceTimeEquation(new SpeedDistanceTimeVariable(SpeedDistanceTimeEquation.Name.SPEED, 0.5),
						new SpeedDistanceTimeVariable(SpeedDistanceTimeEquation.Name.TIME, 1000))));
	}

	@Override
	public void teleopStart() {

	}

	@Override
	public void teleopLoop() {
		ControllerInput controller = (ControllerInput) Input.CONTROLLER_MOVEMENT.getInput();
		ControllerInput controller2 = (ControllerInput) Input.CONTROLLER_ACTIONS.getInput();
		
		EncoderSystem left = (EncoderSystem)Subsystem.LEFT_ENCODER.getSubsystem();
		EncoderSystem right = (EncoderSystem)Subsystem.RIGHT_ENCODER.getSubsystem();

		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		DoubleSolenoidSystem gear_arms = (DoubleSolenoidSystem) Subsystem.GEAR_ARMS.getSubsystem();
		DoubleSolenoidSystem gear_platform = (DoubleSolenoidSystem) Subsystem.GEAR_PLATFORM.getSubsystem();
		SparkSystem climbMotor = (SparkSystem) Subsystem.CLIMB_MOTOR.getSubsystem();

		drive.drive(controller);
		
		System.out.println(left.getDistance() + "/" + right.getDistance());

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
