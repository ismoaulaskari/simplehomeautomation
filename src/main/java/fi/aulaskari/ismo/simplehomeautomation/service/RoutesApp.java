package fi.aulaskari.ismo.simplehomeautomation.service;

import org.apache.camel.builder.RouteBuilder;

public class RoutesApp extends RouteBuilder {

    final String varsBaseDir = "/tmp/simplehomeautomation";
    final String outputDir = varsBaseDir + "/out";
    final String inputDir = varsBaseDir + "/input";
    boolean atHome;
    boolean homeLocked;
    boolean dayLight;
    boolean movementOutside;
    boolean alert;

    public void configure() throws Exception {
        from("quartz://RealTime?cron=0+*+*+*+*+?").log("testing ${date:now:yyyyMMdd}").process(new CheckStateProcessor()).end();

        // Define a simple route
        //from("http://0.0.0.0:8080/hello")
        //      .setBody(constant("Hello World from Apache Camel!"));

        //write status page

        //write camera page/show alert

        //react to files
        from("file:///tmp/simplehomeautomation").process(new InputFileProcessor()).end();
    }
}
