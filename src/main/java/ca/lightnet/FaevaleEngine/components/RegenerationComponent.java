package ca.lightnet.FaevaleEngine.components;

import ca.lightnet.FaevaleEngine.commands.DebugCommand;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Component;
import ca.lightnet.FaevaleEngine.listeners.WildsBlockBreakListener;

public class RegenerationComponent extends Component {

    @Override
    public void onLoad(){
        registerListener(new WildsBlockBreakListener(this.getComponentName()));
        registerCommand(new DebugCommand(this.getComponentName()));
        registerConfig();
    }

    @Override
    public void onSave(){
        this.saveConfig();
    }

    @Override
    public void onUnload(){
        this.saveConfig();
    }
}
