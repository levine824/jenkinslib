package com.levine824.jenkins.config

class ConfigHelper implements Serializable {
    private Script step
    private String name
    private Map args

    ConfigHelper(Script step, Map args = [:]) {
        this.step = step
        this.args = args
        this.name = step.STEP_NAME
        if (!this.name) throw new IllegalArgumentException('Step has no public name property!')
    }

    Map load() {
        Map config = step.env.config.getConfig('step', name)
        Map mergedMap = merge(config, args)
        return mergedMap
    }

    static Map merge(Map config, Map args) {
        Map mergedMap = new HashMap()
        args.each { key, value ->
            if (config.containsKey(key)) {
                if (value instanceof Map) {
                    merge((Map) config[key], value)
                } else if (value instanceof List) {
                    mergedMap.put(key, value)
                } else {
                    mergedMap.put(key, value)
                }
            } else {
                mergedMap.put(key, value)
            }
        }
        return mergedMap
    }

}
