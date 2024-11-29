import groovy.transform.Field

//@Field String STEP_NAME = getClass().getName()
//@Field String METADATA_FILE = 'metadata/maven.yaml'
@Field String buildCommand = 'mvn -B -Dmaven.repo.local=/root/.m2/repository clean install -DskipTests'
@Field String testCommand = 'mvn test'
@Field String deployCommand = 'mvn deploy'


def build(String command) {
    def mvnCommand = command ?: buildCommand
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven build failed with exit code: $exitCode"
    }
}

def test(String command) {
    def mvnCommand = command ?: testCommand
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven test failed with exit code: $exitCode"
    }
}

def deploy(String command) {
    def mvnCommand = command ?: deployCommand
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven deploy failed with exit code: $exitCode"
    }
}