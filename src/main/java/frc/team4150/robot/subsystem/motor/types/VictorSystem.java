package main.java.frc.team4150.robot.subsystem.motor.types;

import edu.wpi.first.wpilibj.Victor;
import main.java.frc.team4150.robot.subsystem.motor.PWMSystem;

public class VictorSystem extends PWMSystem {

    public VictorSystem(int port){
        super(new Victor(port));
    }
}
