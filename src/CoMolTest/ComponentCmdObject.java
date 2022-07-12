package CoMolTest;

import java.util.List;

public class ComponentCmdObject extends AbstractCmdObject{
    public static final String ID = "Component";
    public ComponentCmdObject(String name, List<String> cmdList) {
        super(name, cmdList);
    }
}
