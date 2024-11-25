package com.devops

import java.nio.file.Paths

class ConfigHelper {

    private ConfigObject config

    private ConfigHelper() {}

    static ConfigHelper parse(String path, String env = '') {
        ConfigHelper helper = new ConfigHelper()
        //URL configFile = Paths.get(path).toUri().toURL()
        URL configFile = new File(path).toURI().toURL()
        helper.config = new ConfigSlurper(env).parse(configFile)
        return helper
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
