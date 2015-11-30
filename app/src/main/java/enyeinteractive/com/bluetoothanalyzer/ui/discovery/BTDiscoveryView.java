package enyeinteractive.com.bluetoothanalyzer.ui.discovery;

import java.util.List;

import enyeinteractive.com.bluetoothanalyzer.db.Device;


public interface BTDiscoveryView {
    void showDiscoveredDevices(List<Device> devices);
    void showError(String message);
}
