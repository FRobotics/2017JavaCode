package main.java.frc.team4150.robot.code2018;

import main.java.frc.team4150.robot.input.InputEnum;
import main.java.frc.team4150.robot.input.joystick.ControllerInput;

public enum Input implements InputEnum {
    CONTROLLER_MOVEMENT(new ControllerInput(0)),
    CONTROLLER_ACTIONS(new ControllerInput(1))
    ;
    main.java.frc.team4150.robot.input.InputBase input;
    Input(main.java.frc.team4150.robot.input.InputBase input) {
        this.input = input;
    }

    @Override
    public main.java.frc.team4150.robot.input.InputBase getInput() {
        return input;
    }
}
