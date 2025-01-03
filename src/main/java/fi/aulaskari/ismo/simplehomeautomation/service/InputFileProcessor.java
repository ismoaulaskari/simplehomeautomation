package fi.aulaskari.ismo.simplehomeautomation.service;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputFileProcessor implements org.apache.camel.Processor {
    private static final Logger logger = LoggerFactory.getLogger(InputFileProcessor.class);

    public void process(Exchange exchange) throws Exception {
        logger.info("Reading " + exchange.getIn().getHeader("CamelFileName") + " from " + exchange.getIn().getHeader("CamelFileParent"));
    }
}
