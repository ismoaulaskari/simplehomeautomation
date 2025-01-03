package fi.aulaskari.ismo.simplehomeautomation;

import fi.aulaskari.ismo.simplehomeautomation.service.RoutesApp;
import org.apache.camel.main.Main;

public class RunApp {
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.configure().addRoutesBuilder(new RoutesApp());
        main.run(args);
    }

}
