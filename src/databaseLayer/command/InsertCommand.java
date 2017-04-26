package databaseLayer.command;
import databaseLayer.dao.IDataManager;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class InsertCommand implements ICommand {
    private IDataManager dataManager;
    private Object object;

    @Override
    public boolean execute() {
        return dataManager.add(object);
    }

    @Override
    public void setElement(Object object) {
        this.object = object;
    }

    @Override
    public void setDataManager(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

	@Override
	public boolean undo() {
		return dataManager.delete(object);
	}
}
