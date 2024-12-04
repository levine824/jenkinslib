package com.levine824.jenkins.config

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml

class ConfigLoader {
    static final String DEFAULT_CONFIG = 'config.yml'
    //InputStream is = getClass().getResourceAsStream(DEFAULT_CONFIG)

    static Map parse(String yaml) {
        File file = new File(yaml)
        if (file.exists()) {
            InputStream fis = new FileInputStream(file)
            return load(fis)
        } else {
            throw new FileNotFoundException("配置文件不存在!")
        }
    }

    static Map load(InputStream is) {
        try {
            Yaml yaml = new Yaml()
            Map map = yaml.load(is)
            return map
        } catch (IOException e) {
            throw new Exception("读取配置输入流异常！")
        } finally {
            if (is != null) {
                is.close()
            }
        }
    }

    static Map stageConfig(Map map, String name) {
        return getConfig(map, 'stage', name)
    }

    static Map stepConfig(Map map, String name) {
        return getConfig(map, 'step', name)
    }

    static Map generalConfig(Map map, String name) {
        return getConfig(map, 'general', name)
    }

    private static Map getConfig(Map map, String type, String name) {
        return map?.get(type)?.get(name) ?: [:]
    }
}
