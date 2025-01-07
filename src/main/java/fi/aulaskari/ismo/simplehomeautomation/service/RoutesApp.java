package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RoutesApp extends RouteBuilder {

    @Produce("seda:simpleha_toweb")
    ProducerTemplate toWeb;
    @Produce("seda:simpleha_todebug")
    ProducerTemplate toDebug;

    public void configure() throws Exception {
        Configuration configuration = new Configuration();
        CheckStateProcessor checkStateProcessor = new CheckStateProcessor(configuration);
        checkStateProcessor.setToWeb(toWeb);
        checkStateProcessor.setToDebug(toDebug);
        InputFileProcessor inputFileProcessor = new InputFileProcessor();
        RestInputProcessor restInputProcessor = new RestInputProcessor(configuration);

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
        from("seda:simpleha_toweb").convertBodyTo(String.class).to("file://" + configuration.getWwwOutputDir() + "?filename=out.html").end();
        from("seda:simpleha_todebug").convertBodyTo(String.class).to("file://" + configuration.getOutputDir() + "?filename=out.txt").end();

        //write camera page/show alert

    }
}
