package main.java.frc.team4150.robot.subsystem;

import edu.wpi.first.wpilibj.Counter;
import main.java.frc.team4150.robot.subsystem.base.SubsystemBase;

public class CounterSystem extends SubsystemBase {

	private Counter counter;
	private int count;
	private int lastCount;

	public CounterSystem(int port) {
		counter = new Counter(port);
	}

	public int get() {
		return count;
	}

	public void reset() {
		count = 0;
	}

	@Override
	public void init() {

	}

	@Override
	public void periodic() {

	}

	public void update(boolean direction) {
		if (direction) {
			count += counter.get() - lastCount;
		} else {
			count -= counter.get() - lastCount;
		}
		lastCount = counter.get();
	}

}
