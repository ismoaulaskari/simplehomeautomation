package fi.aulaskari.ismo.simplehomeautomation;

import fi.aulaskari.ismo.simplehomeautomation.service.InputFileProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RoutesApp {
    public static void main(String[] args) throws Exception {

        boolean atHome;
        boolean homeLocked;
        boolean dayLight;
        boolean movementOutside;
        boolean alert;

        // Create a Camel context
        CamelContext context = new DefaultCamelContext();

        // Add routes to the context
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Define a simple route
                from("jetty:http://0.0.0.0:8080/hello")
                        .setBody(constant("Hello World from Apache Camel!"));

                //write status page

                //write camera page/show alert

                //react to files
                from("file:///var/local/simplehomeautomation").process(new InputFileProcessor());
            }
        });

        // Start the Camel context
        context.start();

        // Keep the application running
        Thread.sleep(5000);

        // Stop the context
        context.stop();
    }
}
