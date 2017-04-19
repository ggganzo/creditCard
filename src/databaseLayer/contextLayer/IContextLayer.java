package databaseLayer.contextLayer;

import databaseLayer.AbstractElement;
import databaseLayer.ElementState;
import databaseLayer.dao.IDataManager;
import databaseLayer.command.DataInvoke;
import databaseLayer.command.ICommand;
import databaseLayer.command.StoreCommandFactory;

import java.util.List;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public abstract class IContextLayer<T> {
    private DataInvoke dataInvoke = new DataInvoke();

    public abstract T getElement(int id);
    public abstract List<T> getElements();
    public abstract IDataManager<T> getDataManager();

    public void save(T value) {
        AbstractElement element = (AbstractElement) value;

        if (element == null) return;
        if (element.getElementState() == ElementState.None) return;

        ICommand command = StoreCommandFactory.getInstance().createCommand(element.getElementState());

        command.setDataManager(getDataManager());
        command.setElement(value);

        dataInvoke.setCommand(command);
        dataInvoke.execute();
    }
}
