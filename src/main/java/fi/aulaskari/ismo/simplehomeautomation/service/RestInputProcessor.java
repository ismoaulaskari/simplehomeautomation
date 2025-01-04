package fi.aulaskari.ismo.simplehomeautomation.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestInputProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(RestInputProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Rest input " + exchange.getIn().getBody(String.class));
    }
}
