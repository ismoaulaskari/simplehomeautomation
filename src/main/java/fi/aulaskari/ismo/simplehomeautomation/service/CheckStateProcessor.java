package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import fi.aulaskari.ismo.simplehomeautomation.model.Fact;
import org.apache.camel.Exchange;

public class CheckStateProcessor implements org.apache.camel.Processor {

    private final Configuration conf;

    public CheckStateProcessor(Configuration configuration) {
        this.conf = configuration;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Fact alert = conf.getAlert();
        if (alert.needsHandling()) {

        }
    }
}
