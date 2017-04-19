package databaseLayer;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public abstract class AbstractElement {

    private ElementState elementState = ElementState.None;

    public ElementState getElementState(){
        return elementState;
    }

    public void setElementState(ElementState aElementState){
        elementState = aElementState;
    }
}
