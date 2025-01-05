package fi.aulaskari.ismo.simplehomeautomation.model;

import java.util.HashMap;

public class Site {
    private final HashMap<String, Fact> sensors = new HashMap<>();

    public Fact getSensor(String sensor) {
        return this.sensors.get(sensor);
    }

    public void setSensor(String sensor, Fact value) {
        this.sensors.put(sensor, value);
    }

    public HashMap<String, Fact> getSensors() {
        return sensors;
    }
}
