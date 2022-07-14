package CoMolTest;

import java.util.List;

public class ControlBusCmdObject extends AbstractCmdObject{
    public static final String ID = "ControlBus";
    public ControlBusCmdObject(String name, List<String> cmdList) {
        super(name, cmdList);
    }
}
