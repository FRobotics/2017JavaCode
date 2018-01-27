package main.java.frc.team4150.robot.command.base;

import main.java.frc.team4150.robot.RobotBase;

public abstract class Command {
	
	public abstract void init();

    /**
     * the periodic function for this command
     * @param robot - the robot running the command
     * @return true if the command is finished
     */
    public abstract boolean periodic(RobotBase robot);

}
