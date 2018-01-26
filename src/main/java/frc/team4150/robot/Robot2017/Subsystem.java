package main.java.frc.team4150.robot.Robot2017;

import main.java.frc.team4150.robot.subsystem.CompressorSystem;
import main.java.frc.team4150.robot.subsystem.DoubleSolenoidSystem;
import main.java.frc.team4150.robot.subsystem.DriveSystem;
import main.java.frc.team4150.robot.subsystem.EncoderSystem;
import main.java.frc.team4150.robot.subsystem.SparkSystem;
import main.java.frc.team4150.robot.subsystem.base.SubsystemEnum;

public enum Subsystem implements SubsystemEnum {
    DRIVE(new DriveSystem(0, 1, DriveSystem.MotorType.VICTOR)),
    LEFT_ENCODER(new EncoderSystem(0, 1)),
    RIGHT_ENCODER(new EncoderSystem(2, 3, true)),
    COMPRESSOR(new CompressorSystem(0)),
    CLIMB_BREAK(new DoubleSolenoidSystem(0, 1, DoubleSolenoidSystem.Direction.FORWARD)),
    GEAR_PLATFORM(new DoubleSolenoidSystem(2, 3, DoubleSolenoidSystem.Direction.REVERSE)),
    GEAR_ARMS(new DoubleSolenoidSystem(4, 5, DoubleSolenoidSystem.Direction.REVERSE)),
    CLIMB_MOTOR(new SparkSystem(2));

    private main.java.frc.team4150.robot.subsystem.base.SubsystemBase subsystem;

    Subsystem(main.java.frc.team4150.robot.subsystem.base.SubsystemBase subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public main.java.frc.team4150.robot.subsystem.base.SubsystemBase getSubsystem() {
        return subsystem;
    }

}
