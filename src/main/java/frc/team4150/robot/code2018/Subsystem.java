package main.java.frc.team4150.robot.code2018;

import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.QuadDriveSystem;
import main.java.frc.team4150.robot.subsystem.ShifterSystem;
import main.java.frc.team4150.robot.subsystem.base.SubsystemEnum;
import main.java.frc.team4150.robot.subsystem.motor.TalonSRXSystem;

public enum Subsystem implements SubsystemEnum {
    DRIVE(new QuadDriveSystem(new TalonSRXSystem(10), new TalonSRXSystem(11), new TalonSRXSystem(12), new TalonSRXSystem(13),
    		new EncoderSystem(0, 1, Robot.WHEEL_RADIUS), new EncoderSystem(2, 3, Robot.WHEEL_RADIUS, true),
    		Robot.WHEEL_RADIUS)),
    SHIFTER(new ShifterSystem(0, 1, DoubleSolenoidSystem.Direction.FORWARD, DoubleSolenoidSystem.Direction.REVERSE)),
    ELEVATOR(new TalonSRXSystem(14))
    ;

    private main.java.frc.team4150.robot.subsystem.base.SubsystemBase subsystem;

    Subsystem(main.java.frc.team4150.robot.subsystem.base.SubsystemBase subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public main.java.frc.team4150.robot.subsystem.base.SubsystemBase getSubsystem() {
        return subsystem;
    }

}
