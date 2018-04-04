package main.java.frc.team4150.robot.code2018;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.RobotBase;
import main.java.frc.team4150.robot.command.SetDoubleSolenoidCommand;
import main.java.frc.team4150.robot.command.SetShiftCommand;
import main.java.frc.team4150.robot.command.TimedDriveCommand;
import main.java.frc.team4150.robot.command.TimedMotorCommand;
import main.java.frc.team4150.robot.command.WaitCommand;
import main.java.frc.team4150.robot.command.drive.DriveStraightCommand;
import main.java.frc.team4150.robot.command.drive.DriveStraightTimeoutCommand;
import main.java.frc.team4150.robot.command.drive.TurnCommand;
import main.java.frc.team4150.robot.input.joystick.Axis;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.GyroSystem;
import main.java.frc.team4150.robot.subsystem.drive.DriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.drive.QuadDriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.ShifterSystem;
import main.java.frc.team4150.robot.subsystem.motor.LimitedMotorSystem;

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
		boolean testing = SmartDashboard.getBoolean("testingMode", false);
		SmartDashboard.putNumber("rateTest", SmartDashboard.getNumber("drivePosControl/rate", -1));
		if (testing) {
			// LimitedMotorSystem elevator = (LimitedMotorSystem)
			// Subsystem.ELEVATOR.getSubsystem();
			// this.addCommand(new TimedMotorCommand(elevator, -0.7, 2 * 1000));
			//normalAuto();
			testingAuto();
		} else {
			//baselineAuto();
			normalAuto();
		}
	}

	public void testingAuto() {
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
		GyroSystem gyro = (GyroSystem) Subsystem.GYRO.getSubsystem();

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
					this.addCommand(new TurnCommand(drive, gyro, degrees));
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
	}

	public void baselineAuto() {
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		this.addCommand(new TimedDriveCommand(drive, -0.5, 7 * 1000));
	}

	public void normalAuto() {
		DriveSystem drive = (DriveSystem) Subsystem.DRIVE.getSubsystem();
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();
		LimitedMotorSystem elevator = (LimitedMotorSystem) Subsystem.ELEVATOR.getSubsystem();
		GyroSystem gyro = (GyroSystem) Subsystem.GYRO.getSubsystem();
		ShifterSystem shifter = (ShifterSystem) Subsystem.SHIFTER.getSubsystem();

		boolean colorSwitchSide = false;
		boolean colorScaleSide = false;
		String position = SmartDashboard.getString("position", "left");
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if ((position == "left" && gameData.charAt(0) == 'L') || (position == "right" && gameData.charAt(0) == 'R'))
				colorSwitchSide = true;
			else if ((position == "left" && gameData.charAt(1) == 'L')
					|| (position == "right" && gameData.charAt(1) == 'R'))
				colorScaleSide = true;
		}
		//colorSwitchSide = false;
		colorScaleSide = false;
		int i = 1;
		switch (position) {
			case "right":
				i = -1;
			case "left": {
				if (colorSwitchSide) {
					this.addCommand(new DriveStraightCommand(drive, -140));
					this.addCommand(new TurnCommand(drive, gyro, 90 * i));
					this.addCommand(new TimedMotorCommand(elevator, -1, (int)(1.5 * 1000)));
					this.addCommand(new DriveStraightTimeoutCommand(drive, -25, 3000));
					this.addCommand(new WaitCommand(150));
					this.addCommand(new SetDoubleSolenoidCommand(arm, Direction.REVERSE, 1000));
				} else if (colorScaleSide) {
					this.addCommand(new SetShiftCommand(shifter, true, 500));
					this.addCommand(new DriveStraightCommand(drive, 240, -292, true));
					this.addCommand(new SetShiftCommand(shifter, false, 500));
					this.addCommand(new TurnCommand(drive, gyro, 90 * i));
					this.addCommand(new DriveStraightTimeoutCommand(drive, 18, 3000));
					this.addCommand(new TimedMotorCommand(elevator, -1, 3750));
					this.addCommand(new DriveStraightTimeoutCommand(drive, -28, 3000));
					this.addCommand(new WaitCommand(150));
					this.addCommand(new SetDoubleSolenoidCommand(arm, Direction.REVERSE, 1000));
				} else {
					this.addCommand(new DriveStraightCommand(drive, -140));
				}
				break;
			}
			case "middle": {
				i = gameData.charAt(0) == 'L' ? 1 : -1;
				this.addCommand(new DriveStraightCommand(drive, -40));
				this.addCommand(new TurnCommand(drive, gyro, -90 * i));
				this.addCommand(new DriveStraightCommand(drive, -47));
				this.addCommand(new TurnCommand(drive, gyro, 90 * i));
				this.addCommand(new TimedMotorCommand(elevator, -1, (int)(1.5 * 1000)));
				this.addCommand(new DriveStraightTimeoutCommand(drive, -112, 2000));
				this.addCommand(new WaitCommand(150));
				this.addCommand(new SetDoubleSolenoidCommand(arm, Direction.REVERSE, 1000));
				break;
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
		DoubleSolenoidSystem arm = (DoubleSolenoidSystem) Subsystem.ARM.getSubsystem();

		drive.customDrive(controller, false);

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

		if (controller.getAxis(Axis.TRIGGER_LEFT) > 0.5) {
			shifter.setShift(true);
		} else if (controller.getAxis(Axis.TRIGGER_RIGHT) > 0.5) {
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
		GyroSystem gyro = (GyroSystem) Subsystem.GYRO.getSubsystem();
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
		SmartDashboard.putNumber("vars/gyro", gyro.getAngle());
	}

	@Override
	public void periodic() {
		LimitedMotorSystem elevator = (LimitedMotorSystem) Subsystem.ELEVATOR.getSubsystem();
		DoubleSolenoidSystem elevatorBrake = (DoubleSolenoidSystem) Subsystem.ELEVATOR_BRAKE.getSubsystem();
		if (elevator.getMotor().getSpeed() != 0) {
			elevatorBrake.setDirection(Direction.REVERSE);
		} else {
			elevatorBrake.setDirection(Direction.FORWARD);
		}
	}
}
