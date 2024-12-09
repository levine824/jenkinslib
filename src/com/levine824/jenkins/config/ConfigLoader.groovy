package com.levine824.jenkins.config

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml

class ConfigLoader {

    static Map parse(String yaml) {
        try {
            return new Yaml().load(yaml)
        } catch (FileNotFoundException ignored) {
            throw new Exception("配置文件未找到!")
        } catch (Exception e) {
            throw new Exception("读取配置文件错误:" + e)
        }
    }

    static Map load(InputStream io) {
        Map config = [:]
        try {
            io.with {
                config = new Yaml().load(io)
            }
            return config
        } catch (IOException ignored) {
            throw new Exception("读取配置文件流异常!")
        } catch (Exception e) {
            throw new Exception("读取配置文件流错误:" + e)
        }
    }

    static Map getConfig(Map config, ConfigType type, String name) {
        return config?.get(type.toString())?.get(name) ?: [:]
    }

    static Map getConfig(Map config, Map<ConfigType, Set<String>> keySets) {
        Map map = [:]
        keySets.each { type, keySet ->
            Map typeConfig = (Map) config?.get(type.toString()) ?: [:]
            for (String key : keySet) {
                if (typeConfig.containsKey(key) && !map.containsKey(key)) {
                    map.put(key, typeConfig.get(key))
                } else {
                    throw new Exception("配置项" + key + "不存在或者重复!")
                }
            }
        }
        return map
    }

}
