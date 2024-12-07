package com.levine824.jenkins.configuration

class ConfigurationHelper implements Serializable {
    private Script step
    private String name
    private String type

    private Map config

    static ConfigurationHelper newInstance(Script step, Map config = [:]) {
        return new ConfigurationHelper(step, config)
    }

    private ConfigurationHelper(Script step, Map config) {
        this.step = step
        this.config = config ?: [:]
        this.type = step.CONFIGURATION_TYPE
        if (!this.type) throw new IllegalArgumentException('There has no public type property!')

        this.name = type
        if (!this.name || !this.type) {
            throw new IllegalArgumentException('There has no public name or type property!')
        }
    }
}
