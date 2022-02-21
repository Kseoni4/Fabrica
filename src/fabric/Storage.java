package fabric;

import java.util.ArrayList;

/**
 * Хранилище произведённых объектов класса {@link Device} - они же - устройства.
 */
public class Storage {

    private ArrayList<Device> devicesContainer = new ArrayList<>();

    public void putDeviceInto(Device device){
        devicesContainer.add(device);
    }

    public ArrayList<Device> getDevicesContainer(){
        return devicesContainer;
    }

}
