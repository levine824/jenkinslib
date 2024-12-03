package com.levine824.jenkins.config

class ConfigHelper {

    private Script step
    private String name
    private Map config

    private ConfigHelper(Script step, Map config) {
        this.step = step
        this.config = config
        this.name = step.STEP_NAME
    }

    
}
