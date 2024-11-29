package com.levine824.jenkinslibrary

import groovy.yaml.YamlSlurper
import java.nio.file.Path
import java.nio.file.Paths

class ConfigurationLoader {
    
    static Map loadYaml(String path) {
        Path p = Paths.get(path)
        if (!p.exists()) {
            throw new FileNotFoundException('Path not found!')
        }
        Map config = new YamlSlurper().parse(p)
        return config
    }
}
