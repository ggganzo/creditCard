package databaseLayer.command;

import databaseLayer.ElementState;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public interface CommandFactory {
    ICommand createCommand(ElementState elementState);
}
