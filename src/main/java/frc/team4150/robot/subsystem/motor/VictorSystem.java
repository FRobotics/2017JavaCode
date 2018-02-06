package main.java.frc.team4150.robot.subsystem.motor;

import edu.wpi.first.wpilibj.Victor;

public class VictorSystem extends PWMSpeedControllerSystem {

    public VictorSystem(int port){
        super(new Victor(port));
    }
}
