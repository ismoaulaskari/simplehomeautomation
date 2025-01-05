package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RoutesApp extends RouteBuilder {

    Configuration configuration = new Configuration();
    CheckStateProcessor checkStateProcessor = new CheckStateProcessor(configuration);
    InputFileProcessor inputFileProcessor = new InputFileProcessor();
    RestInputProcessor restInputProcessor = new RestInputProcessor(configuration);

    public void configure() throws Exception {
        restConfiguration().component("jetty").host("localhost").port("8080").bindingMode(RestBindingMode.json);

        //react to rest
        from("rest:put:" + configuration.getRestBaseUrl() + "/items/{item}").process(restInputProcessor).end();
        //legacy
        from("rest:put:" + configuration.getRestBaseUrl() + "/items/{item}/state").process(restInputProcessor).end();

        //react to files
        from("file:///tmp/simplehomeautomation").process(inputFileProcessor).end();

        //handle actions
        from("quartz://RealTime?cron=0+*+*+*+*+?").log("testing ${date:now:yyyyMMdd_HH:mm:ss}").process(checkStateProcessor).end();

        //write status page
        from("seda:simpleha_toweb").convertBodyTo(String.class).to("file://" + configuration.getOutputDir()).end();

        //write camera page/show alert

    }
}
