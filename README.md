# simplehomeautomation
File-based home controller that doesn't break on system upgrades, customizable via home-grown scripts

-java -should we use Camel standalone for file operations or is it too fragile and dependent on changing java versions?
-no open ports, or localhost camel rest api, file based input mostly and initiates itself all web calls -create your own integrations, we don't provide them
-local alerts via home siren, but how to contact remote android phone reliably? Web Alert android app on a single
-no graphical user interface. only code, conf files and command line!
-modelling site via java? affects actions handling and status pages

published status page?

Running: mvn clean compile exec:java -Dexec.mainClass=fi.aulaskari.ismo.simplehomeautomation.RunApp
