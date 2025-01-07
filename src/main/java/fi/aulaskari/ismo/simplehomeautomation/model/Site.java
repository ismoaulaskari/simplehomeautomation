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

    @Override
    public String toString() {
        return "Site{" +
                "sensors=" + sensors +
                '}';
    }


    public String toHtmlStatusPage() {
        String sensorsHtml = "";
        for (String sensorKey : sensors.keySet()) {
            sensorsHtml += "<th>" + sensorKey + "</th>";
        }
        sensorsHtml += "</tr><tr>";

        for (String sensorKey : sensors.keySet()) {
            String bgcolor = "white";
            if (!sensors.get(sensorKey).expired()) {
                bgcolor = "yellow";
                if (!sensors.get(sensorKey).needsHandling()) {
                    bgcolor = "red";
                }
            }

            sensorsHtml += "<td bgcolor=\"" + bgcolor + "\">" + sensors.get(sensorKey).toHtmlStatusPage() + "</td>";
        }
        return "<h3>Sensors</h3> <p><table border=\"1\"><tr> " + sensorsHtml + " </tr></table></p>\n";
    }
}
