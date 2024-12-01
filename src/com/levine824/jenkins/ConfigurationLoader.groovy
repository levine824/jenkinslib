package com.levine824.jenkins

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml

class ConfigurationLoader {

    private Map config

    private ConfigurationLoader() {}

    static ConfigurationLoader parse(String path) {
        File file = new File(path);
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file)
            return parse(inputStream)
        } else {
            throw new FileNotFoundException("配置文件不存在！")
        }
    }

    static ConfigurationLoader parse(InputStream inputStream) {
        ConfigurationLoader loader = new ConfigurationLoader()
        try {
            Yaml yaml = new Yaml();
            loader.config = yaml.load(inputStream);
            return loader
        } catch (IOException e) {
            throw new Exception("读取配置输入流异常！")
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }

    Map stageConfiguration(String name) {
        return loadConfiguration('stage', name)
    }

    Map stepConfiguration(String name) {
        return loadConfiguration('step', name)
    }

    Map generalConfiguration(String name) {
        return loadConfiguration('general', name)
    }

    private Map loadConfiguration(String type, String name) {
        return config?.get(type)?.get(name) ?: [:]
    }

}
