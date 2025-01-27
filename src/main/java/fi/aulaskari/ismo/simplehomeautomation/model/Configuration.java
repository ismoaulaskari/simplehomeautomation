package fi.aulaskari.ismo.simplehomeautomation.model;

import java.util.Calendar;
import java.util.Date;

public class Configuration {
    final String restBaseUrl = "rest";
    final String wwwOutputDir = "/home/aulaskar/Downloads/";
    final String varsBaseDir = "/tmp/simplehomeautomation";
    final String outputDir = varsBaseDir + "/out";
    final String inputDir = varsBaseDir + "/input";
    final Site site = new Site();
    Fact atHome = new Fact("ATHOME", FactType.YES, "Initial");
    Fact homeLocked = new Fact("HOMELOCKED", FactType.NO, "Initial");
    Fact dayLight;
    Fact movementOutside;
    Fact alert;
    Fact alarm;

    //add known sensors here
    public Configuration() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 1);
        Date future = cal.getTime();
        site.setSensor("FRONTDOOR", new Fact("FRONTDOOR", FactType.DOOR, "CLOSED"));
        site.setSensor("GARAGEDOOR", new Fact("GARAGEDOOR", FactType.DOOR, "CLOSED"));
        site.setSensor("HALLWAYDOOR", new Fact("HALLWAYDOOR", FactType.DOOR, "CLOSED"));
        site.setSensor("PORCHDOOR", new Fact("PORCHDOOR", FactType.DOOR, "CLOSED"));
        site.setSensor("BACKDOOR", new Fact("BACKDOOR", FactType.DOOR, "CLOSED"));
        //site.setSensor("THRASHCAN", new Fact("TRASHCAN", FactType.MOVEMENT_OUTDOOR, "DETECTED", future));
        //site.setSensor("KIDSROOM", new Fact("KIDSROOM", FactType.MOVEMENT, "DETECTED", future));
        //site.setSensor("ITEM", new Fact("ITEM", FactType.ALARM, null));*/
    }

    public void refreshFacts() {
        if (atHome != null && atHome.expired()) {
            atHome = new Fact();
        }
        if (homeLocked != null && homeLocked.expired()) {
            homeLocked = new Fact();
        }
        if (dayLight != null && dayLight.expired()) {
            dayLight = new Fact();
        }
        if (movementOutside != null && movementOutside.expired()) {
            movementOutside = new Fact();
        }
        if (alert != null && alert.expired()) {
            alert = new Fact();
        }
        if (alarm != null && alarm.expired()) {
            alarm = new Fact();
        }

    }

    public String getWwwOutputDir() {
        return wwwOutputDir;
    }

    public Site getSite() {
        return site;
    }

    public String getRestBaseUrl() {
        return restBaseUrl;
    }

    public Fact getAlarm() {
        return alarm;
    }

    public void setAlarm(Fact alarm) {
        this.alarm = alarm;
    }

    public String getVarsBaseDir() {
        return varsBaseDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getInputDir() {
        return inputDir;
    }

    public Fact getAtHome() {
        return atHome;
    }

    public void setAtHome(Fact atHome) {
        this.atHome = atHome;
    }

    public Fact getHomeLocked() {
        return homeLocked;
    }

    public void setHomeLocked(Fact homeLocked) {
        this.homeLocked = homeLocked;
    }

    public Fact getDayLight() {
        return dayLight;
    }

    public void setDayLight(Fact dayLight) {
        this.dayLight = dayLight;
    }

    public Fact getMovementOutside() {
        return movementOutside;
    }

    public void setMovementOutside(Fact movementOutside) {
        this.movementOutside = movementOutside;
    }

    public Fact getAlert() {
        return alert;
    }

    public void setAlert(Fact alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "restBaseUrl='" + restBaseUrl + '\'' +
                ", varsBaseDir='" + varsBaseDir + '\'' +
                ", outputDir='" + outputDir + '\'' +
                ", inputDir='" + inputDir + '\'' +
                ", site=" + site +
                ", atHome=" + atHome +
                ", homeLocked=" + homeLocked +
                ", dayLight=" + dayLight +
                ", movementOutside=" + movementOutside +
                ", alert=" + alert +
                ", alarm=" + alarm +
                '}';
    }

    public String toHtmlStatusPage() {
        return "<p><a href=\"#\" onclick=\"showhide('conf1');\">Params</a><div id=\"conf1\" style=\"display: none;\">restBaseUrl='" + restBaseUrl + '\'' +
                ", varsBaseDir='" + varsBaseDir + '\'' +
                ", outputDir='" + outputDir + '\'' +
                ", wwwOutputDir='" + wwwOutputDir + '\'' +
                ", inputDir='" + inputDir + '\'' + "</div></p>" +
                "<h3>Sensors</h3><p> site=" + site.toHtmlStatusPage() +
                "</p><p>, atHome=" + atHome +
                ", homeLocked=" + homeLocked +
                ", dayLight=" + dayLight +
                ", movementOutside=" + movementOutside +
                ", alert=" + alert +
                ", alarm=" + alarm +
                "</p>";
    }

}
