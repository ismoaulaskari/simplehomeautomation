package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import fi.aulaskari.ismo.simplehomeautomation.model.Fact;
import fi.aulaskari.ismo.simplehomeautomation.model.FactType;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class CheckStateProcessor implements org.apache.camel.Processor {

    ProducerTemplate toWeb;
    ProducerTemplate toDebug;

    private final Configuration conf;

    public CheckStateProcessor(Configuration configuration) {
        this.conf = configuration;
    }

    public void setToDebug(ProducerTemplate toDebug) {
        this.toDebug = toDebug;
    }

    public void setToWeb(ProducerTemplate toWeb) {
        this.toWeb = toWeb;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        //@TODO iterative logic., we might find a better way to do this
        //athome, homelocked, daylight
        conf.refreshFacts(); //drop expired actions, but not sensors?

        Fact alarm = conf.getAlarm();
        if (alarm != null && alarm.needsHandling()) {
//do sth with producertemplate ? @TODO
            //siren? Cellphone? @TODO
        }

        Fact alert = conf.getAlert();
        if (alert != null && alert.needsHandling()) {
            if (conf.getHomeLocked().isActive()) {
                createAlert(alert);
//do sth with producertemplate? @TODO
                //notification to phone? @TODO
                alert.setForwarded(new Date());
            } else {
//soft notify?
            }
        }

        Fact movementOutside = conf.getMovementOutside();
        if (movementOutside != null && movementOutside.needsHandling()) {
            if (conf.getHomeLocked().isActive() || !conf.getAtHome().isActive()) {
                if (conf.getAlert().needsHandling()) {
//do sth with producertemplate ? @TODO
                }

            }

        }

        for (Fact sensor : conf.getSite().getSensors().values()) {
            if (sensor.needsHandling()) {
                switch (sensor.getType()) {
                    case DOOR:
                        createAlert(sensor);
                        break;
                    case WINDOW:
                        createAlert(sensor);
                        break;
                    default: //@TODO send notification
                }
                sensor.setForwarded(new Date());
                break;
            }
        }

        //draw status page
        String htmlSnippet = conf.toHtmlStatusPage();
        String template = Files.readString(Path.of("src/main/resources/templates/trafficlightpage.html"));
        String htmlPage = template.replace("<!--INSERT_BODY_HERE-->", htmlSnippet);
        Exchange toWebExc = exchange.copy();
        toWebExc.getIn().setBody(htmlPage);
        toWeb.send(toWebExc);

        String page = conf.toString();
        Exchange toStringExc = exchange.copy();
        toStringExc.getIn().setBody(page);
        toDebug.send(toStringExc);

    }

    private void createAlert(Fact alert) {
        Fact newAlert = new Fact(alert.getName(), FactType.ALERT, alert.getState());
        conf.setAlert(newAlert);
    }
}
