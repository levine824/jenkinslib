package com.devops

class ConfigurationLoader implements Serializable {

    static Properties loadConfiguration(Script script, String path) {
        def fileStr = script.readFile encoding: 'UTF-8', file: "${path}"

        Properties props = new Properties()
        try {
            props.load(new StringReader(fileStr))
        } catch (IOException e) {
            throw new Exception("读取配置文件错误:" + e)
        }

        return props
    }

}
