package com.levine
@Grab('org.codehaus.groovy:groovy-yaml:3.0.23')
import groovy.yaml.YamlSlurper
import java.nio.file.Path
import java.nio.file.Paths

class ConfigurationLoader {
    
    static def loadYaml(String path) {
        Path p = Paths.get(path)
        if (!p.exists()) {
            throw new FileNotFoundException('Path not found!')
        }
        def config = new YamlSlurper().parse(p)
        return config
    }
}
