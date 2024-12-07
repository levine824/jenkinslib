package com.levine824.jenkins.configuration

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml

class ConfigurationLoader {
    private Map config

    private ConfigurationLoader(Map config = [:]) {
        this.config = config
    }


    static String forTest(){
        return "success"
    }

    static ConfigurationLoader parse(String yaml) {
        try {
            Map config = new Yaml().load(yaml)
            return new ConfigurationLoader(config)
        } catch (FileNotFoundException ignored) {
            throw new Exception("配置文件未找到!")
        } catch (Exception e) {
            throw new Exception("读取配置文件错误:" + e)
        }
    }

    static ConfigurationLoader load(InputStream io) {
        Map config = [:]
        try {
            io.with {
                config = new Yaml().load(io)
            }
            return new ConfigurationLoader(config)
        } catch (IOException ignored) {
            throw new Exception("读取配置文件流异常!")
        } catch (Exception e) {
            throw new Exception("读取配置文件流错误:" + e)
        }
    }

    Map getConfiguration(ConfigurationType type, String name) {
        return config?.get(type.toString())?.get(name) ?: [:]
    }

}
