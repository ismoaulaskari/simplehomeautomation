package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import fi.aulaskari.ismo.simplehomeautomation.model.Fact;
import fi.aulaskari.ismo.simplehomeautomation.model.FactType;
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
        String input = exchange.getIn().getBody(String.class);
        switch (item) {
            case "home": //dp stj with special case
                break;
            default:
                if (configuration.getSite().getSensor(item) != null) {
                    //    configuration.getSite().getSensor(item)
                    //create some kind of fact?
                    Fact f = configuration.getSite().getSensor(item);
                    //set new fact?
                    configuration.getSite().setSensor(item, new Fact(item, FactType.valueOf(item), input)); //@TODO ned a new object?
                    //@TODO how to know if alert etc?? register for actions?

                }
        }
    }
}
