package com.levine824.jenkins

import com.levine824.jenkins.config.ConfigLoader

class PipelineEnvironment {
    Map config
    Map defaultConfig

    PipelineEnvironment(String path) {
        this.config = path ? [:] : ConfigLoader.parse(path)
        this.defaultConfig = ConfigLoader.load(getClass().getResourceAsStream('config.yml'))
    }

}
