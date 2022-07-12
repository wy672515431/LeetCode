package CoMolTest;

import java.util.List;

public class AbstractCmdObject {
    private String name;
    private List<String> cmdList;
    public AbstractCmdObject (String name, List<String> cmdList) {
        this.name = name;
        this.cmdList = cmdList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<String> cmdList) {
        this.cmdList = cmdList;
    }
}
