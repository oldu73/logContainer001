import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Container {

    // Map to store components. The component identifier is key.
    private Map<String, Object> components;

    public Container() {
        components = new HashMap<>();

        try {

            // The property file is not read in the correct order (code comments below)

            /*
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\main\\resources\\components.properties"));
            for (Map.Entry entry : properties.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                processEntry(key, value);
            }
            */

            // Good implementation follow, which use Apache Commons Configuration
            // SRC:
            // - http://stackoverflow.com/questions/1312383/pulling-values-from-a-java-properties-file-in-order
            // - http://commons.apache.org/proper/commons-configuration/
            // - http://wilddiary.com/reading-property-file-java-using-apache-commons-configuration/
            // - http://www.jmdoudoux.fr/java/dej/chap-apache_commons.htm

            final Configuration config = new PropertiesConfiguration("components.properties");

            Iterator<String> keys = config.getKeys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = (String) config.getProperty(key);
                processEntry(key, value);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void processEntry(String key, String value) throws Exception {
        String[] parts = key.split("\\.");

        if (parts.length == 1) {
            // Define new component.
            Object component = Class.forName(value).newInstance();
            components.put(parts[0], component);
        } else {
            // Dependency injection
            Object component = components.get(parts[0]);
            Object reference = components.get(value);
            PropertyUtils.setProperty(component, parts[1], reference);
        }

    }

    public Object getComponents(String id) {
        return components.get(id);
    }

}
