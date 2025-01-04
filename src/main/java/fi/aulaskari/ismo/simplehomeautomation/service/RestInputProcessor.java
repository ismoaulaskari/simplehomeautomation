package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestInputProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(RestInputProcessor.class);
    private final Configuration configuration;

    public RestInputProcessor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Rest input " + exchange.getIn().getHeader("CamelHttpUri") + " " + exchange.getIn().getHeader("item") + " " + exchange.getIn().getBody(String.class));
        String item = exchange.getIn().getHeader("item", String.class);
        switch (item) {
            case "home": //dp stj with special case
                break;
            default:
                if (configuration.getSite().getSensor(item) != null) {
                }
        }
    }
}
