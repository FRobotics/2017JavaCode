package main.java.frc.team4150.robot.command.base;

import main.java.frc.team4150.robot.RobotBase;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandGroup extends Command {

    private ArrayList<Command> commands;

    public CommandGroup(){
        this.commands = new ArrayList<>();
    }

    /**
     * Adds a command to the list of commands you want to execute 
     * @param command - the command you want to add
     * @return
     */
    public CommandGroup add(Command command){
        commands.add(command);
        return this;
    }

    @Override
    public boolean periodic(RobotBase robot) {
        boolean allFinished = true;
        for (Iterator<Command> it = commands.iterator(); it.hasNext(); ) {
            Command command = it.next();
            if(!command.periodic(robot)) allFinished = false;
            else it.remove();
        }
        return allFinished;
    }

	@Override
	public void init() {
		for(Command command : commands) {
			command.init();
		}
	}
}
