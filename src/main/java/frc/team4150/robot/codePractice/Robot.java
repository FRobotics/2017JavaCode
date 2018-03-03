package main.java.frc.team4150.robot.codePractice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.input.joystick.Axis;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.QuadDriveSystem;

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
		addCommand(new DriveStraightCommand(drive, 2 * 12));
		/*
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
		}*/
	}

	@Override
	public void teleopStart() {

	}

	@Override
	public void teleopLoop() {

		ControllerInput controller = (ControllerInput) Input.CONTROLLER_MOVEMENT.getInput();
		ControllerInput controller2 = (ControllerInput) Input.CONTROLLER_ACTIONS.getInput();
		
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
		QuadDriveSystem drive = (QuadDriveSystem) Subsystem.DRIVE.getSubsystem();
		//EncoderSystem leftEncoder = drive.getLeftEncoder();
		//EncoderSystem rightEncoder = drive.getRightEncoder();
		
		if (controller2.getAxis(Axis.TRIGGER_LEFT) > 0.5) {
			arm.setDirection(Direction.REVERSE);
		} else if (controller2.getAxis(Axis.TRIGGER_RIGHT) > 0.5) {
			arm.setDirection(Direction.FORWARD);
		}
		
		SmartDashboard.putNumber("leftMotor", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("rightMotor", drive.getRightMotor().getSpeed());

		drive.customDrive(controller, true);
	}

	@Override
	public void stopLoop() {
		SmartDashboard.putNumber("leftMotor", 0);
		SmartDashboard.putNumber("rightMotor", 0);
	}
}
