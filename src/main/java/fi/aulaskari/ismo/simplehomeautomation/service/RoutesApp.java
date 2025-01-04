package fi.aulaskari.ismo.simplehomeautomation.service;

import fi.aulaskari.ismo.simplehomeautomation.model.Configuration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RoutesApp extends RouteBuilder {

    Configuration configuration = new Configuration();
    CheckStateProcessor checkStateProcessor = new CheckStateProcessor(configuration);
    InputFileProcessor inputFileProcessor = new InputFileProcessor();
    RestInputProcessor restInputProcessor = new RestInputProcessor();

    public void configure() throws Exception {
        restConfiguration().component("jetty").host("localhost").port(8080).bindingMode(RestBindingMode.json);

        from("rest:put:rest/items").process(restInputProcessor).end();

        from("quartz://RealTime?cron=0+*+*+*+*+?").log("testing ${date:now:yyyyMMdd}").process(checkStateProcessor).end();

        // Define a simple route
        //from("http://0.0.0.0:8080/hello")
        //      .setBody(constant("Hello World from Apache Camel!"));

        //write status page

        //write camera page/show alert

        //react to files
        from("file:///tmp/simplehomeautomation").process(inputFileProcessor).end();
    }
}
