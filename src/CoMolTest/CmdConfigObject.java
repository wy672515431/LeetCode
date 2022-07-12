package CoMolTest;

import java.util.List;

public class CmdConfigObject {
    private List<FlexChipCmdObject> flexChips;
    private List<ComponentCmdObject> components;
    private List<ControlBusCmdObject> controlBuses;
    public CmdConfigObject(List<FlexChipCmdObject> flexChips,
                           List<ComponentCmdObject> components,
                           List<ControlBusCmdObject> controlBuses) {
        this.flexChips = flexChips;
        this.components = components;
        this.controlBuses = controlBuses;
    }

    public List<FlexChipCmdObject> getFlexChips() {
        return flexChips;
    }

    public void setFlexChips(List<FlexChipCmdObject> flexChips) {
        this.flexChips = flexChips;
    }

    public List<ComponentCmdObject> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentCmdObject> components) {
        this.components = components;
    }

    public List<ControlBusCmdObject> getControlBuses() {
        return controlBuses;
    }

    public void setControlBuses(List<ControlBusCmdObject> controlBuses) {
        this.controlBuses = controlBuses;
    }
}
