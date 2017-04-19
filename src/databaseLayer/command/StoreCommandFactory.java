package databaseLayer.command;

import databaseLayer.ElementState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class StoreCommandFactory implements CommandFactory {
    private static CommandFactory instance = new StoreCommandFactory();
    private static Map<ElementState, ICommand> storeCommands = new HashMap<>();

    static {
        storeCommands.put(ElementState.Inserted, new InsertCommand());
        storeCommands.put(ElementState.Updated, new UpdateCommand());
        storeCommands.put(ElementState.Deleted, new DeleteCommand());
    }

    private StoreCommandFactory(){}

    public static CommandFactory getInstance(){
        return instance;
    }

    @Override
    public ICommand createCommand(ElementState elementState) {
        if(storeCommands.containsKey(elementState))
            return storeCommands.get(elementState);

        return null;
    }
}
