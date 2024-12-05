package com.levine824.jenkins.config

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml

class ConfigLoader {
    //private static final String DEFAULT_CONFIG = 'config.yml'
    //InputStream is = getClass().getResourceAsStream(DEFAULT_CONFIG)
    private Map config

    static ConfigLoader parse(String yaml) {
        File file = new File(yaml)
        if (file.exists()) {
            InputStream fis = new FileInputStream(file)
            return load(fis)
        } else {
            throw new FileNotFoundException("配置文件不存在!")
        }
    }

    static ConfigLoader load(InputStream is) {
        try {
            Yaml yaml = new Yaml()
            Map map = yaml.load(is)
            return new ConfigLoader(map)
        } catch (IOException e) {
            throw new Exception("读取配置输入流异常！")
        } finally {
            if (is != null) {
                is.close()
            }
        }
    }

    private ConfigLoader(Map config) {
        this.config = config
    }

    Map generalConfig(String name) {
        return getConfig('general', name)
    }

    Map stageConfig(String name) {
        return getConfig('stage', name)
    }

    Map stepConfig(String name) {
        return getConfig('step', name)
    }

    private Map getConfig(String type, String name) {
        return config?.get(type)?.get(name) ?: [:]
    }
}