package main.java.frc.team4150.robot.code2018;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.SetDoubleSolenoidCommand;
import main.java.frc.team4150.robot.command.TimedDriveCommand;
import main.java.frc.team4150.robot.command.TimedMotorCommand;
import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.command.drive.TurnCommand;
import main.java.frc.team4150.robot.input.joystick.Axis;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
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
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
		
		boolean testing = SmartDashboard.getBoolean("testingMode", false);
		if (testing) {
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
						boolean open = Boolean.parseBoolean(parts[1]);
						double wait = Double.parseDouble(parts[2]);
						Direction direction;
						if (open) {
							direction = Direction.FORWARD;
						} else {
							direction = Direction.REVERSE;
						}
						this.addCommand(new SetDoubleSolenoidCommand(arm, direction, (long) (wait * 1000)));
						break;
					}
				}
			}
		} else {
			baselineAuto();
		}
	}
	
	public void baselineAuto() {
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		this.addCommand(new TimedDriveCommand(drive, -0.5, 7 * 1000));
	}
	
	public void normalAuto() {
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
		LimitedMotorSystem elevator = (LimitedMotorSystem) Subsystem.ELEVATOR.getSubsystem();
		
		boolean colorSwitchSide = false;
		boolean colorScaleSide = false;
		String position = SmartDashboard.getString("position", "middle");
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if ((position == "left" && gameData.charAt(0) == 'L') || (position == "right" && gameData.charAt(0) == 'R'))
				colorSwitchSide = true;
			else if ((position == "left" && gameData.charAt(1) == 'L')
					|| (position == "right" && gameData.charAt(1) == 'R'))
				colorScaleSide = true;
		}
		int i = -1;
		switch (position) {
			case "left":
				i = -1;
			case "right": {
				if (colorSwitchSide) {
					this.addCommand(new DriveStraightCommand(drive, 152));
					this.addCommand(new TurnCommand(drive, 90, position == "left"));
					this.addCommand(new DriveStraightCommand(drive, 18));
					this.addCommand(new TimedMotorCommand(elevator, 0.5, 3 * 1000));
					this.addCommand(new SetDoubleSolenoidCommand(arm, Direction.FORWARD, 1000));
				} else if (colorScaleSide) {
					this.addCommand(new DriveStraightCommand(drive, 312));
					this.addCommand(new TurnCommand(drive, 90, position == "left"));
					this.addCommand(new TimedMotorCommand(elevator, 0.5, 3 * 1000));
					this.addCommand(new DriveStraightCommand(drive, 18));
					this.addCommand(new SetDoubleSolenoidCommand(arm, Direction.FORWARD, 1000));
				} else {
					this.addCommand(new DriveStraightCommand(drive, 152));
				}
			}
			case "middle": {
				this.addCommand(new DriveStraightCommand(drive, 40));
				this.addCommand(new TurnCommand(drive, 90, gameData.charAt(0) == 'L'));
				this.addCommand(new DriveStraightCommand(drive, 54));
				this.addCommand(new TurnCommand(drive, 90, gameData.charAt(0) == 'R'));
				this.addCommand(new DriveStraightCommand(drive, 112));
				this.addCommand(new TimedMotorCommand(elevator, 0.5, 3 * 1000));
				this.addCommand(new SetDoubleSolenoidCommand(arm, Direction.FORWARD, 1000));
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
		ShifterSystem shifter = (ShifterSystem) Subsystem.SHIFTER.getSubsystem();

		LimitedMotorSystem elevator = (LimitedMotorSystem) Subsystem.ELEVATOR.getSubsystem();
		DoubleSolenoidSystem elevatorBrake = (DoubleSolenoidSystem) Subsystem.ELEVATOR_BRAKE.getSubsystem();
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();

		drive.customDrive(controller, false);

		if (elevator.getMotor().getSpeed() != 0) {
			elevatorBrake.setDirection(Direction.REVERSE);
		} else {
			elevatorBrake.setDirection(Direction.FORWARD);
		}

		if (controller2.buttonPressed(Button.A)) {
			arm.setDirection(Direction.REVERSE);
		} else if (controller2.buttonPressed(Button.B)) {
			arm.setDirection(Direction.FORWARD);
		}

		if (controller2.getAxis(Axis.LEFT_Y) < -0.5) {
			elevator.setSpeed(-1.0);
		} else if (controller2.getAxis(Axis.LEFT_Y) > 0.5) {
			elevator.setSpeed(0.75);
		} else {
			elevator.stop();
		}

		if (controller2.getAxis(Axis.TRIGGER_LEFT) > 0.5) {
			shifter.setShift(true);
		} else if (controller2.getAxis(Axis.TRIGGER_RIGHT) > 0.5) {
			shifter.setShift(false);
		}
	}

	@Override
	public void stopLoop() {

	}

	@Override
	public void updateNTVariables() {
		QuadDriveSystem drive = (QuadDriveSystem) Subsystem.DRIVE.getSubsystem();
		EncoderSystem leftEncoder = drive.getLeftEncoder();
		EncoderSystem rightEncoder = drive.getRightEncoder();
		LimitedMotorSystem elevator = (LimitedMotorSystem) Subsystem.ELEVATOR.getSubsystem();
		ShifterSystem shifter = (ShifterSystem) Subsystem.SHIFTER.getSubsystem();
		DoubleSolenoidSystem elevatorBrake = (DoubleSolenoidSystem) Subsystem.ELEVATOR_BRAKE.getSubsystem();
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
		SmartDashboard.putNumber("vars/motors/left/speed", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("vars/motors/right/speed", drive.getRightMotor().getSpeed());
		SmartDashboard.putNumber("vars/motors/left/encoder", leftEncoder.getDistance());
		SmartDashboard.putNumber("vars/motors/right/encoder", rightEncoder.getDistance());
		SmartDashboard.putNumber("vars/elevator/count", elevator.getCount());
		SmartDashboard.putBoolean("vars/motors/highGear", shifter.isHigh());
		SmartDashboard.putBoolean("vars/motors/slowMode", shifter.isLow());
		SmartDashboard.putBoolean("vars/elevator/brake", elevatorBrake.getDirection() == Direction.FORWARD);
		SmartDashboard.putBoolean("vars/goClimb", false);

		SmartDashboard.putString("vars/arm/state", arm.getDirection() == Direction.FORWARD ? "open" : "closed");
		SmartDashboard.putNumber("vars/elevator/height", elevator.getCount());
		SmartDashboard.putNumber("vars/gyro", 0);
	}
}
