package fi.aulaskari.ismo.simplehomeautomation.model;

public class Configuration {
    final String varsBaseDir = "/tmp/simplehomeautomation";
    final String outputDir = varsBaseDir + "/out";
    final String inputDir = varsBaseDir + "/input";
    Fact atHome;
    Fact homeLocked;
    Fact dayLight;
    Fact movementOutside;
    Fact alert;
    Fact alarm;

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
}
