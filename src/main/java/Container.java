import org.apache.commons.beanutils.PropertyUtils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class Container {

    // Map to store components. The component identifier is key.
    private Map<String, Object> components;

    public Container() {
        components = new HashMap<>();

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\main\\resources\\components.properties"));
            for (Map.Entry entry : properties.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
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
