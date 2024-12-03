package com.levine824.jenkins.config

@Grab('org.yaml:snakeyaml:2.3')
import org.yaml.snakeyaml.Yaml
import com.levine824.jenkins.PipelineEnvironment


class ConfigLoader {

    static final String DEFAULT_CONFIG = 'config.yml'

    static Map parse(String yaml) {
        File file = new File(yaml)
        if (file.exists()) {
            InputStream fis = new FileInputStream(file)
            return load(fis)
        } else {
            throw new FileNotFoundException("配置文件不存在！")
        }
    }

    static Map loadDefault() {
        InputStream is = getClass().getResourceAsStream(DEFAULT_CONFIG)
        return load(is)
    }

    private static Map load(InputStream is) {
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

    static Map stageConfig(PipelineEnvironment env, String name) {
        return getConfig(env, 'stage', name)
    }

    static Map stepConfig(PipelineEnvironment env, String name) {
        return getConfig(env, 'step', name)
    }

    static Map generalConfig(PipelineEnvironment env, String name) {
        return getConfig(env, 'general', name)
    }

    private static Map getConfig(PipelineEnvironment env, String node, String name) {
        return env?.config?.get(node)?.get(name) ?: [:]
    }
}
