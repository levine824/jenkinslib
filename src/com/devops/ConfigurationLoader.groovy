package com.devops


class ConfigurationLoader {

    static Map loadConfiguration(Script script, String filePath) {
        def props = loadConfigurationWithoutQuotes(script, filePath)
        def map = props.collectEntries { key, value -> [(key): value] }
        return map
    }

    static Map loadConfigurationByPrefix(Script script, String filePath, String prefix) {
        def props = loadConfigurationWithoutQuotes(script, filePath)
        def map = props.findAll { key, value -> key.startsWith(prefix) }
        return map
    }

    //@NonCPS
    private static Properties loadConfigurationWithoutQuotes(Script script, String filePath) {
        def configFile = new File(filePath)
        if (!configFile.exists()) {
            script.error "配置文件 ${filePath} 不存在"
        }

        def props = new Properties()
        configFile.withInputStream { stream -> props.load(stream) }

        props.each { key, value -> props[key] = value.replaceAll("^\"|\"\$", "") }

        return props
    }

}
