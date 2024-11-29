import groovy.transform.Field

//@Field String STEP_NAME = getClass().getName()

def call(Map stepParams) {
    sh """ 
        sonar-scanner \
        -Dsonar.projectKey=${stepParams.projectName} \
        -Dsonar.projectName=${stepParams.projectName} \
        -Dsonar.projectVersion=${stepParams.projectVersion} \
        -Dsonar.ws.timeout=30 \
        -Dsonar.sources=${stepParams.projectPath} \
        -Dsonar.sourceEncoding=UTF-8 \
        -Dsonar.language=${stepParams.projectLanguage} \
    """
}
