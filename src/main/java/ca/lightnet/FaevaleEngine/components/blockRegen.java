package ca.lightnet.FaevaleEngine.components;

import ca.lightnet.FaevaleEngine.commands.DebugCommand;
import ca.lightnet.FaevaleEngine.libs.Models.Objects.Component;
import ca.lightnet.FaevaleEngine.listeners.WildsBlockListener;

public class blockRegen extends Component {

    @Override
    public void onLoad(){
        registerListener(new WildsBlockListener());
        registerCommand(new DebugCommand());
    }

    @Override
    public void onSave(){

    }

    @Override
    public void onUnload(){

    }
}
