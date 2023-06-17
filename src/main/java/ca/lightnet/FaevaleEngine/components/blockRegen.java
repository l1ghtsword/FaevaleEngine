package ca.lightnet.FaevaleEngine.components;

import ca.lightnet.FaevaleEngine.libs.Models.Objects.Component;
import ca.lightnet.FaevaleEngine.listeners.WildsBlockListener;

public class blockRegen extends Component {

    @Override
    public void onLoad(){
        registerListener(new WildsBlockListener());
    }

    @Override
    public void onSave(){

    }

    @Override
    public void onUnload(){

    }
}
