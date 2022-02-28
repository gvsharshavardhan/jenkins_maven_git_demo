package mytests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Test {
    final static Logger l = Logger.getLogger(Test.class);
    public static void main(String[] args) {

        PropertyConfigurator.configure("./src/test/resources/log4j.properties");
        l.info("harsha");
        l.warn("harsha");
        l.fatal("harsha");
    }
}
