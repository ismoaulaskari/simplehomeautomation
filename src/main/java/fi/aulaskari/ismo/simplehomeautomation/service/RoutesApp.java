package fi.aulaskari.ismo.simplehomeautomation.service;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RoutesApp extends RouteBuilder {

    final String varsBaseDir = "/var/local/simplehomeautomation";
    final String outputDir = varsBaseDir + "/out";
    final String inputDir = varsBaseDir + "/input";
    boolean atHome;
    boolean homeLocked;
    boolean dayLight;
    boolean movementOutside;
    boolean alert;


    public void configure() throws Exception {
        from("quartz://RealTime?cron=0+*+*+*+*+?").process(new CheckStateProcessor());

        // Define a simple route
        //from("http://0.0.0.0:8080/hello")
        //      .setBody(constant("Hello World from Apache Camel!"));

        //write status page

        //write camera page/show alert

        //react to files
        from("file:///var/local/simplehomeautomation").process(new InputFileProcessor());
    }
}