package main.java.frc.team4150.robot.subsystem.motor.types;

import edu.wpi.first.wpilibj.Spark;
import main.java.frc.team4150.robot.subsystem.motor.PWMSystem;

public class SparkSystem extends PWMSystem {

    public SparkSystem(int port){
        super(new Spark(port));
    }
}
