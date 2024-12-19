package com.levine824.jenkins.config

import org.yaml.snakeyaml.Yaml

class ConfigLoader {

    private Map config

    static ConfigLoader load(String yaml) {
        try {
            Map config = new Yaml().load(yaml)
            return new ConfigLoader(config)
        } catch (Exception e) {
            throw new Exception("读取配置文件错误:" + e.getMessage())
        }
    }

    private ConfigLoader(Map config) {
        this.config = config
    }

    Map getConfig(ConfigType type, String key = null) {
        try {
            if (key == null) {
                if (config instanceof Map<String, Map>) {
                    return config?.get(type.toString()) ?: [:]
                }
            } else {
                if (config instanceof Map<String, Map<String, Map>>) {
                    return config?.get(type.toString())?.get(key) ?: [:]
                }
            }
            throw new IllegalArgumentException("配置项" + type + "格式错误!")
        } catch (MissingPropertyException ignored) {
            return [:]
        }
    }
}
