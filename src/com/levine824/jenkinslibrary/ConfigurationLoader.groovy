package com.levine824.jenkinslibrary

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml

class ConfigurationLoader {

    static final String DEFAULT_CONFIGURATION = '/default-configuration.yml'

    static Map loadConfiguration(String path) {
        return loadYaml(path, false)
    }

    static Map loadDefaultConfiguration() {
        return loadYaml("", true)
    }

    private static Map loadYaml(String path, boolean isDefault) {
        InputStream inputStream = null
        try {
            inputStream = isDefault ? this.class.getResourceAsStream(DEFAULT_CONFIGURATION) : new FileInputStream(path)
            Yaml yaml = new Yaml();
            Map data = yaml.load(inputStream);
            return data
        } catch (IOException e) {
            return [:]
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }

}
