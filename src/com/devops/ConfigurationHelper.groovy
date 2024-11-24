package com.devops

class ConfigurationHelper {

    static setEnvProperty(Script script, Map map) {
        map.each { key, value -> script.env.setProperty(key, value) }
    }

    static removePrefix(Map map, String prefix) {
        def mapWithoutPrefix = [:]
        map.each { key ->
            if (key.startsWith(prefix)) {
                def keyWithoutPrefix = key.substring(prefix.length())
                mapWithoutPrefix[keyWithoutPrefix] = map[key]
            }
        }

        return mapWithoutPrefix
    }

}
