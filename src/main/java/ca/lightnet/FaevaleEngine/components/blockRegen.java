package ca.lightnet.FaevaleEngine.components;

import ca.lightnet.FaevaleEngine.commands.DebugCommand;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Component;
import ca.lightnet.FaevaleEngine.listeners.WildsBlockListener;

public class blockRegen extends Component {

    @Override
    public void onLoad(){
        registerListener(new WildsBlockListener());
        registerCommand(new DebugCommand());
    }

    @Override
    public void onSave(){
        //unimplemented
    }

    @Override
    public void onUnload(){
        //unimplemented
    }
}
