package fi.aulaskari.ismo.simplehomeautomation.model;

public class Configuration {
    final String varsBaseDir = "/tmp/simplehomeautomation";
    final String outputDir = varsBaseDir + "/out";
    final String inputDir = varsBaseDir + "/input";
    boolean atHome;
    boolean homeLocked;
    boolean dayLight;
    boolean movementOutside;
    boolean alert;

    public String getVarsBaseDir() {
        return varsBaseDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getInputDir() {
        return inputDir;
    }

    public boolean isAtHome() {
        return atHome;
    }

    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
    }

    public boolean isHomeLocked() {
        return homeLocked;
    }

    public void setHomeLocked(boolean homeLocked) {
        this.homeLocked = homeLocked;
    }

    public boolean isDayLight() {
        return dayLight;
    }

    public void setDayLight(boolean dayLight) {
        this.dayLight = dayLight;
    }

    public boolean isMovementOutside() {
        return movementOutside;
    }

    public void setMovementOutside(boolean movementOutside) {
        this.movementOutside = movementOutside;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
