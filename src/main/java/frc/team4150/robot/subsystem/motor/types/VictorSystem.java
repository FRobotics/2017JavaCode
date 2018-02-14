package main.java.frc.team4150.robot.subsystem.motor.types;

import edu.wpi.first.wpilibj.Victor;
import main.java.frc.team4150.robot.subsystem.motor.PWMSpeedControllerSystem;

public class VictorSystem extends PWMSpeedControllerSystem {

    public VictorSystem(int port){
        super(new Victor(port));
    }
}
