package main.java.frc.team4150.robot.codePractice;

import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem.Direction;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;
import main.java.frc.team4150.robot.subsystem.base.SubsystemEnum;
import main.java.frc.team4150.robot.subsystem.drive.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.drive.QuadDriveSystem;
import main.java.frc.team4150.robot.subsystem.motor.types.JaguarSystem;

public enum Subsystem implements SubsystemEnum {
    DRIVE(new QuadDriveSystem(new JaguarSystem(0), new JaguarSystem(1), new JaguarSystem(2), new JaguarSystem(3),
    		new EncoderSystem(0, 1, Robot.WHEEL_RADIUS), new EncoderSystem(2, 3, Robot.WHEEL_RADIUS, true),
    		Robot.WHEEL_RADIUS)),
    ARM(new DoubleSolenoidSystem(0, 1, Direction.REVERSE))
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
