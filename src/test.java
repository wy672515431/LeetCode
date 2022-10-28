import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {
        File file = new File("./test.rule");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FlexDeviceModelCheckInfo flexDeviceModelCheckInfo = new FlexDeviceModelCheckInfo();
                flexDeviceModelCheckInfo.setIndex(1);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(flexDeviceModelCheckInfo);
                objectOutputStream.close();
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                FlexDeviceModelCheckInfo map = (FlexDeviceModelCheckInfo) objectInputStream.readObject();
                objectInputStream.close();
                System.out.println(map.getIndex());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } 
        }
    }
}

class FlexDeviceModelCheckInfo implements Serializable{
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}