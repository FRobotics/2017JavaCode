package main.java.frc.team4150.robot.code2018;

import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.LimitedMotorSystem;
import main.java.frc.team4150.robot.subsystem.QuadDriveSystem;
import main.java.frc.team4150.robot.subsystem.ShifterSystem;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.subsystem.base.SubsystemEnum;
import main.java.frc.team4150.robot.subsystem.motor.TalonSRXSystem;

public enum Subsystem implements SubsystemEnum {
    DRIVE(new QuadDriveSystem(new TalonSRXSystem(10), new TalonSRXSystem(11), new TalonSRXSystem(12), new TalonSRXSystem(13),
    		new EncoderSystem(0, 1, Robot.WHEEL_RADIUS), new EncoderSystem(2, 3, Robot.WHEEL_RADIUS, true),
    		Robot.WHEEL_RADIUS)),
    SHIFTER(new ShifterSystem(0, 1, DoubleSolenoidSystem.Direction.FORWARD, DoubleSolenoidSystem.Direction.REVERSE)),
    ELEVATOR(new LimitedMotorSystem(new TalonSRXSystem(14), 1, 1)) //TODO: make the ports correct
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
