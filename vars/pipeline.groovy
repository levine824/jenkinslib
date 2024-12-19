def call(Map args) {
    pipeline {
        agent none

        options {}

        environment {}

        stages {
            stage('Init') {
                steps {
                    pipelineStageInit script: args.script, configFile: args.configFile
                }
            }



        }

    }
}
