def call(Script script) {
    def type = script.getProperty('CONFIGURATION_TYPE')
    echo "${type}"
}