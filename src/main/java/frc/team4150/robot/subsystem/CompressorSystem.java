package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Compressor;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class CompressorSystem extends SubsystemBase {

    private Compressor compressor;

    public CompressorSystem(int module){
        this.compressor = new Compressor(module);
    }

    @Override
    public void init() {
        compressor.start();
    }

	@Override
	public void periodic() {
		
	}
}
