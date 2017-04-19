package databaseLayer.command;

import databaseLayer.dao.IDataManager;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public interface ICommand {
    boolean execute();
    void setElement(Object object);
    void setDataManager(IDataManager dataManager);
}
