package com.levine824.jenkins.config

import com.levine824.jenkins.utils.MapUtils

class ConfigHelper implements Serializable {
    private Script step
    private String name
    private Map config

    static ConfigHelper newInstance(Script step, Map config = [:]) {
        return new ConfigHelper(step, config)
    }

    private ConfigHelper(Script step, Map config) {
        this.step = step
        this.config = config ?: [:]
        this.name = step.getProperty('STEP_NAME')
        if (!this.name) throw new IllegalArgumentException('Step has no public name property!')
    }

    ConfigHelper loadStepConfig(Map config, Map args = [:]) {
        Map stepConfig = ConfigLoader.getConfig(config, ConfigType.STEP, name)
        this.config = MapUtils.merge(stepConfig, args)
        return this
    }

}
