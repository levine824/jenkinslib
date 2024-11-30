package com.levine824.jenkinslibrary

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml


import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ConfigurationLoader {
    Map loadYaml(String path) {
        try (InputStream inputStream = new FileInputStream(path)) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);
            return data
        } catch (IOException e) {
            return [:]
        }
    }
}