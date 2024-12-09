package com.levine824.jenkins.config

import com.levine824.jenkins.utils.MapUtils

class ConfigHelper implements Serializable {
    private Script step
    private String name
    private Map config

    static ConfigHelper newInstance(Script step, Map config = [:]) {
        return new ConfigHelper(step, config)
    }

    private ConfigHelper(Script step, Map config) {
        this.step = step
        this.config = config ?: [:]
        this.name = step.STEP_NAME
        if (!this.name) throw new IllegalArgumentException('Step has no public name property!')
    }

    Map getConfig(Map args = [:]) {
        Map stepConfig = ConfigLoader.getConfig(config, ConfigType.STEP, name)
        Map mergedConfig = args.size() == 0 ? stepConfig : MapUtils.merge(stepConfig, args)
        return mergedConfig.putAll(ConfigLoader.getConfig(config, getKeySets(ConfigType.STEP)))
    }

    private Map<ConfigType, Set<String>> getKeySets(ConfigType without = null) {
        Map<ConfigType, Set<String>> map = [:]
        for (ConfigType type : ConfigType.values()) {
            if (type == without) continue
            def field = step.getProperty(type.toField())
            if (field != null && field instanceof Set && !field.isEmpty()) {
                map.put(type, field)
            }
        }
        return map
    }

}
