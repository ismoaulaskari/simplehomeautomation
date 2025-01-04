package fi.aulaskari.ismo.simplehomeautomation.model;

import java.util.Date;

public class Fact {
    String name;
    boolean active;
    Date startDate;
    Date endDate;
    Date forwarded;

    public Fact() {
    }

    public Fact(String name) {
        this.name = name;
        this.active = true;
        this.startDate = new Date();

    }

    public Fact(String name, Date endDate) {
        this.name = name;
        this.active = true;
        this.startDate = new Date();
        this.endDate = endDate;
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
}
