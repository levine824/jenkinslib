package com.levine824.jenkins

import com.levine824.jenkins.config.ConfigLoader

class PipelineEnvironment {
    private static final String DEFAULT_CONFIG = 'config.yml'

    private ConfigLoader config

    PipelineEnvironment(String yaml = "") {
        loadConfig(yaml)
    }

    private loadConfig(String yaml) {
        if (yaml == null || yaml.trim().isEmpty()) {
            InputStream is = getClass().getResourceAsStream(DEFAULT_CONFIG)
            this.config = ConfigLoader.load(is)
        } else {
            this.config = ConfigLoader.parse(yaml)
        }
    }



}
