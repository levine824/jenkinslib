import java.nio.file.Files
import java.nio.file.Paths

class ConfigHelper {

    private String env
    private ConfigObject config

    ConfigHelper() {
        this.env = ''
    }

    ConfigHelper(String env) {
        this.env = env
    }

    ConfigObject parse(String path) {
        URL configFile = Files.createFile(Paths.get(path)).toUri().toURL()
        this.config = new ConfigSlurper(env).parse(configFile)
        return config
    }

    ConfigObject setEnvProperty(Script script) {
        ConfigObject formattedConfig = new ConfigObject()
        config.each { key, value ->
            String formattedKey = key.toString().replace('.', '_').toUpperCase()
            script.env.setProperty(formattedKey, value)
            formattedConfig[formattedKey] = value
        }
        return formattedConfig
    }

}
