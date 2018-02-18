package main.java.frc.team4150.robot.subsystem.motor.types;

import edu.wpi.first.wpilibj.Jaguar;
import main.java.frc.team4150.robot.subsystem.motor.PWMSystem;

public class JaguarSystem extends PWMSystem {

	public JaguarSystem(int id) {
		super(new Jaguar(id));
	}
	
}
