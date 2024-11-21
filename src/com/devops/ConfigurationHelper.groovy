package com.devops

class ConfigurationHelper implements Serializable {

    static Properties removeQuotes(Properties props) {
        def propsWithoutQuotes = new Properties()

        props.stringPropertyNames().forEach(key -> {
            def valueWithoutQuotes = props.getProperty(key).replaceAll("^\"|\"\$", "")
            propsWithoutQuotes.setProperty(key, valueWithoutQuotes)
        })

        return propsWithoutQuotes
    }

    // Iterate properties and set variables "${key}=${value}"
    static setVariables(Script script, Properties props) {
        props.stringPropertyNames().forEach(key -> {
            def value = props.getProperty(key)
            script "${key}=${value}"
        })
    }

}
