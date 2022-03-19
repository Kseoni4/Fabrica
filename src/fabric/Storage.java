package fabric;

import java.util.ArrayList;

/**
 * Хранилище произведённых объектов класса {@link Device} и {@link MegaDevice} - они же - устройства.
 */
public class Storage {

    private ArrayList<Device> devicesContainer = new ArrayList<>();

    private ArrayList<Container> deviceContainers = new ArrayList<>();

    public void putDeviceInto(Device device){
        devicesContainer.add(device);
    }

    public void putContainerInto(Container container){
        deviceContainers.add(container);
    }

    public ArrayList<Device> getDevicesContainer(){
        return devicesContainer;
    }

    public ArrayList<Container> getDeviceContainers(){
        return deviceContainers;
    }

}
