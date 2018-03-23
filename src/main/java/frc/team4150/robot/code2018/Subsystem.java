package main.java.frc.team4150.robot.code2018;

import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.subsystem.base.SubsystemEnum;
import main.java.frc.team4150.robot.subsystem.drive.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.drive.QuadDriveSystem;
import main.java.frc.team4150.robot.subsystem.drive.ShifterSystem;
import main.java.frc.team4150.robot.subsystem.motor.LimitedMotorSystem;
import main.java.frc.team4150.robot.subsystem.motor.types.TalonSRXSystem;

public enum Subsystem implements SubsystemEnum {
    DRIVE(new QuadDriveSystem(new TalonSRXSystem(10), new TalonSRXSystem(11), new TalonSRXSystem(12), new TalonSRXSystem(13),
    		new EncoderSystem(0, 1, Robot.WHEEL_RADIUS), new EncoderSystem(2, 3, Robot.WHEEL_RADIUS, true),
    		Robot.WHEEL_RADIUS)),
    SHIFTER(new ShifterSystem(0, 1, Direction.FORWARD, Direction.REVERSE)),
    ELEVATOR(new LimitedMotorSystem(new TalonSRXSystem(14), 5, 6, 4)),
    ARM(new DoubleSolenoidSystem(4, 5, Direction.FORWARD)),
    ELEVATOR_BRAKE(new DoubleSolenoidSystem(2, 3, Direction.REVERSE))
    ;

    private SubsystemBase subsystem;

    Subsystem(SubsystemBase subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public SubsystemBase getSubsystem() {
        return subsystem;
    }

}
