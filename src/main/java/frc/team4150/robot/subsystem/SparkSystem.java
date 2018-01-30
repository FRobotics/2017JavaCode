package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Spark;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class SparkSystem extends SubsystemBase {

    private Spark spark;
    private double speed;

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
        this.speed = speed;
    }
    
    /**
     * Sets the speed of the spark motor to 0
     */
    public void stop() {
		this.speed = 0;
	}
	
    /**
     * Reverses the speed of the spark motor
     */
	public void reverse() {
		this.speed *= -1;
	}

	@Override
	public void periodic() {
		spark.setSpeed(speed);
	}
}
