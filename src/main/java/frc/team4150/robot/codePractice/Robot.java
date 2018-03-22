package main.java.frc.team4150.robot.codePractice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.SetDoubleSolenoidCommand;
import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.command.drive.TurnCommand;
import main.java.frc.team4150.robot.input.joystick.Axis;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.QuadDriveSystem;
import main.java.frc.team4150.robot.subsystem.motor.types.JaguarSystem;
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
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
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
					SmartDashboard.putBoolean("worked", true);
					boolean open = Boolean.parseBoolean(parts[1]);
					double wait = Double.parseDouble(parts[2]);
					Direction direction;
					if(open) {
						direction = Direction.FORWARD;
					} else {
						direction = Direction.REVERSE;
					}
					this.addCommand(new SetDoubleSolenoidCommand(arm, direction, (long)(wait * 1000)));
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
		
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
		QuadDriveSystem drive = (QuadDriveSystem) Subsystem.DRIVE.getSubsystem();
		JaguarSystem lm1 = (JaguarSystem)Subsystem.DISC_LAUNCHER_MOTOR_1.getSubsystem();
		JaguarSystem lm2 = (JaguarSystem)Subsystem.DISC_LAUNCHER_MOTOR_2.getSubsystem();
		
		if (controller2.getAxis(Axis.TRIGGER_LEFT) > 0.5) {
			arm.setDirection(Direction.REVERSE);
		} else if (controller2.getAxis(Axis.TRIGGER_RIGHT) > 0.5) {
			arm.setDirection(Direction.FORWARD);
		}
		
		if (controller2.buttonDown(Button.RIGHT_BUMPER)) {
			lm1.setSpeed(-1);
			lm2.setSpeed(-1);
		} else {
			lm1.setSpeed(0);
			lm2.setSpeed(0);
		}

		drive.customDrive(controller, true);
	}

	@Override
	public void stopLoop() {
	}
	
	public void updateNTVariables () {
		QuadDriveSystem drive = (QuadDriveSystem) Subsystem.DRIVE.getSubsystem();
		SmartDashboard.putNumber("vars/motors/left/speed", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("vars/motors/right/speed", drive.getRightMotor().getSpeed());
	}
}
