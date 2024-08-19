package dao;

import java.util.List;
import model.Device;

public interface DeviceDAO {
    void registerDevice(Device device);
    List<Device> getDevicesByUserId(int userId);
    List<Device> searchDevices(String query);
    List<Device> getAllDevices();
	void deleteRegisteredProduct(int regId);
}
