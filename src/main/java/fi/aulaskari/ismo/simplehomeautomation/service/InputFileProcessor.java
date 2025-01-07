package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import fi.aulaskari.ismo.simplehomeautomation.model.Fact;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class InputFileProcessor implements org.apache.camel.Processor {
    private static final Logger logger = LoggerFactory.getLogger(InputFileProcessor.class);
    private final Configuration configuration;

    public InputFileProcessor(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Reading " + exchange.getIn().getHeader("CamelFileName") + " from " + exchange.getIn().getHeader("CamelFileParent"));
        String item = exchange.getIn().getHeader("CamelFileParent", String.class);
        String input = exchange.getIn().getBody(String.class);
        switch (item) {
            default:
                if (configuration.getSite().getSensor(item) != null) {
                    Fact fact = configuration.getSite().getSensor(item);
                    fact.setActive(true);
                    fact.setStartDate(new Date());
                    fact.setState(input);
                    fact.setForwarded(null);
                    //@TODO how to know if alert etc?? register for actions?

                }
        }
    }
}
}
