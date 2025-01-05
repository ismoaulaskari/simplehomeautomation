package fi.aulaskari.ismo.simplehomeautomation.model;

import java.util.Date;

public class Fact {
    String name;
    FactType type;
    boolean active;
    Date startDate;
    Date endDate;
    Date forwarded;
    String state;

    public Fact() {
    }

    public Fact(String name, FactType type, String state) {
        this.name = name;
        this.type = type;
        this.active = true;
        this.startDate = new Date();
        this.state = state;
    }

    public Fact(String name, FactType type, String state, Date endDate) {
        this.name = name;
        this.type = type;
        this.active = true;
        this.startDate = new Date();
        this.endDate = endDate;
        this.state = state;
    }

    public boolean expired() {
        return (this.endDate != null && this.getEndDate().before(new Date()));
    }

    public boolean needsHandling() {
        return (this.isActive() && this.getEndDate() != null && this.getEndDate().after(new Date()) && this.getForwarded() == null);
    }

    public Date getForwarded() {
        return forwarded;
    }

    public void setForwarded(Date forwarded) {
        this.forwarded = forwarded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FactType getType() {
        return type;
    }

    public void setType(FactType type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", active=" + active +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", forwarded=" + forwarded +
                ", state='" + state + '\'' +
                '}';
    }
}
