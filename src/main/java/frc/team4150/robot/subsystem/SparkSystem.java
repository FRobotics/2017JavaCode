package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Spark;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class SparkSystem extends SubsystemBase {

    private Spark spark;

    public SparkSystem(int channel){
        spark = new Spark(channel);
    }

    @Override
    public void init() {

    }

    /**
     * Sets the speed of the spark motor
     * @param speed
     */
    public void setSpeed(double speed){
        spark.setSpeed(speed);
    }

	@Override
	public void periodic() {
		
	}
}
