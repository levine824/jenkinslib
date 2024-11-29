package com.levine

//@Grab('org.yaml:snakeyaml:2.3')
//import org.yaml.snakeyaml.Yaml

@Grab('org.codehaus.groovy:groovy-yaml:3.0.23')
import groovy.yaml.YamlSlurper
import java.nio.file.Path
import java.nio.file.Paths

class ConfigurationLoader {

    static def loadYaml(String path) {
        Path p = Paths.get(path)
        boolean isExists = Files.exists(p)
        if (!isExists) {
            throw new FileNotFoundException('Path not found!')
        }
        def config = new YamlSlurper().parse(p)
        return config
    }
}
