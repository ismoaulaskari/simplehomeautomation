package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import fi.aulaskari.ismo.simplehomeautomation.model.Fact;
import org.apache.camel.Exchange;

import java.util.Date;

public class CheckStateProcessor implements org.apache.camel.Processor {

    private final Configuration conf;

    public CheckStateProcessor(Configuration configuration) {
        this.conf = configuration;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        //athome, homelocked, daylight
        conf.refreshFacts(); //drop expired actions, but not sensors?

        Fact alarm = conf.getAlarm();
        if (alarm != null && alarm.needsHandling()) {
//do sth with producertemplate ?
            //siren? Cellphone?
        }

        Fact alert = conf.getAlert();
        if (alert != null && alert.needsHandling()) {
            if (conf.getHomeLocked().isActive()) {
                Fact newAlarm = new Fact("alarm");
                conf.setAlarm(newAlarm);
//do sth with newAlarmproducertemplate?
                //notification to phone?
                alert.setForwarded(new Date());
            }

        }

        Fact movementOutside = conf.getMovementOutside();
        if (movementOutside != null && movementOutside.needsHandling()) {
            if (conf.getHomeLocked().isActive() || !conf.getAtHome().isActive()) {
                if (conf.getAlert().needsHandling()) {
//do sth with producertemplate ?
                }

            }

        }

        for (Fact sensor : conf.getSite().getSensors().values()) {
            if (sensor.needsHandling()) {

            }
        }

    }
}
