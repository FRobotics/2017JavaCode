package main.java.frc.team4150.robot.command.base;

import main.java.frc.team4150.robot.RobotBase;

import java.util.ArrayList;

public class CommandManager {

    private boolean paused;
    private ArrayList<Command> commands;
    private Command current;

    public CommandManager() {
        this.paused = false;
        this.commands = new ArrayList<>();
        this.current = null;
    }

    /**
     * Adds a command to the list of commands to be executed
     * @param command
     */
    public void push(Command command) {
    	if(current == null) setCurrent(command);
    	else this.commands.add(command);
    }

    /**
     * This should be run in the periodic for whatever modes you use commands in
     * @param robot
     */
    public void periodic(RobotBase robot) {
        if (!paused) {
            if (current != null && current.periodic(robot)) {
                if (commands.size() > 0) setCurrent(commands.remove(0));
                else current = null;
            }
        }
    }
    
    public void setCurrent(Command command) {
    	current = command;
    	current.init();
    }

    public void clear() {
    	current = null;
    	this.commands.clear();
    }
    
}
