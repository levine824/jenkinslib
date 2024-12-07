package com.levine824.jenkins.config

import com.levine824.jenkins.PipelineEnvironment

class ConfigHelper implements Serializable {
    private Script step
    private String name
    private Map args

    static ConfigHelper newInstance(Script step, Map args = [:]) {
        new ConfigHelper(step, args)
    }

    private ConfigHelper(Script step, Map args) {
        this.step = step
        this.args = args ?: [:]
        this.name = step.STEP_NAME
        if (!this.name) throw new IllegalArgumentException('Step has no public name property!')
    }

    Map getConfig(PipelineEnvironment pipe) {
        Map config = pipe.getConfig().stepConfig(name)
        return merge(config, args)
    }

    private static Map merge(Map config, Map args) {
        Map mergedMap = config
        if (!args.isEmpty()) {
            args.each { key, value ->
                if (config.containsKey(key)) {
                    if (value instanceof Map) {
                        mergedMap.put(key, merge((Map) config[key], value))
                    } else if (value instanceof List) {
                        mergedMap.put(key, value)
                    } else {
                        mergedMap.put(key, value)
                    }
                } else {
                    mergedMap.put(key, value)
                }
            }
        }
        return mergedMap
    }

}
