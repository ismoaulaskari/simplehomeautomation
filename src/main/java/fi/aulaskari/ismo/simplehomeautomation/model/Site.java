package fi.aulaskari.ismo.simplehomeautomation.model;

import java.util.HashMap;

public class Site {
    private final HashMap<String, String> sensors = new HashMap<>();

    public String getSensor(String sensor) {
        return this.sensors.get(sensor);
    }

    public void setSensor(String sensor, String value) {
        this.sensors.put(sensor, value);
    }
}
