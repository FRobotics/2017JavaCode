package main.java.frc.team4150.robot.code2018;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team4150.robot.input.joystick.Button;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.subsystem.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.ShifterSystem;
import main.java.frc.team4150.robot.util.Distance;
import main.java.frc.team4150.robot.util.Distance.Unit;
import main.java.frc.team4150.robot.util.PositionControl;

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
		ShifterSystem shifter = (ShifterSystem) Subsystem.SHIFTER.getSubsystem();

		drive.customDrive(controller, true);
		//TODO: switch to controller 2
		if(controller.buttonPressed(Button.A)) {
			shifter.setShift(true);
		} else if(controller.buttonPressed(Button.B)) {
			shifter.setShift(false);
		}

		SmartDashboard.putNumber("leftMotor", drive.getLeftMotor().getSpeed());
		SmartDashboard.putNumber("rightMotor", drive.getRightMotor().getSpeed());
		SmartDashboard.putNumber("leftEncoder", leftEncoder.getDistance().to(Unit.FEET));
		SmartDashboard.putNumber("rightEncoder", rightEncoder.getDistance().to(Unit.FEET));

		// TODO: deal with other subsystems based on input
	}
	
	public PositionControl createDriveControl(Distance distance) {
		Distance target = new Distance(SmartDashboard.getNumber("drivePosControl/target", distance.to(Unit.FEET)), Unit.FEET);
		double minSpeed = SmartDashboard.getNumber("drivePosControl/minSpeed", 0);
		double maxSpeed = SmartDashboard.getNumber("drivePosControl/maxSpeed", 0);
		double rate = SmartDashboard.getNumber("drivePosControl/rate", 0);
		double deadband = SmartDashboard.getNumber("drivePosControl/deadband", 0);
		return new PositionControl(target, minSpeed, maxSpeed, rate, deadband);
	}
}
