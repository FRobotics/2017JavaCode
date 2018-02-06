package main.java.frc.team4150.robot.subsystem.motor;

import edu.wpi.first.wpilibj.Spark;

public class SparkSystem extends PWMSpeedControllerSystem {

    public SparkSystem(int port){
        super(new Spark(port));
    }
}
