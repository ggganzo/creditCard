package databaseLayer.command;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class DataInvoke {
    private ICommand command;

    public void setCommand(ICommand command){
        this.command = command;
    }

    public void execute(){
        command.execute();
    }
}
