package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import fi.aulaskari.ismo.simplehomeautomation.model.Fact;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class RestInputProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(RestInputProcessor.class);
    private final Configuration configuration;

    public RestInputProcessor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Rest input " + exchange.getIn().getHeader("CamelHttpUri") + " " + exchange.getIn().getHeader("item") + " " + exchange.getIn().getBody(String.class));
        String path = exchange.getIn().getHeader("CamelHttpUri", String.class);
        String item = exchange.getIn().getHeader("item", String.class);
        String input = exchange.getIn().getBody(String.class);
        switch (path) {
            case "/rest/items/home": //dp stj with special case
                break;
            default:
                if (configuration.getSite().getSensor(item) != null) {
                    //    configuration.getSite().getSensor(item)
                    //create some kind of fact?
                    Fact fact = configuration.getSite().getSensor(item);
                    fact.setActive(true);
                    fact.setStartDate(new Date());
                    fact.setState(input);
                    fact.setForwarded(null);
                    //@TODO how to know if alert etc?? register for actions?
                    if (path.matches("/rest/s/.*")) {
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.MINUTE, 1);
                        Date future = cal.getTime();
                        fact.setEndDate(future);
                    }
                }
        }
    }
}
