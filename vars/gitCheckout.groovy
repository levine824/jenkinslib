import groovy.transform.Field

//@Field String STEP_NAME = getClass().getName()

def call(Map stepParams) {
    checkout scmGit(
            branches: [[name: stepParams.gitBranch]],
            extensions: [cleanBeforeCheckout(deleteUntrackedNestedRepositories: true)],
            userRemoteConfigs: [[credentialsId: stepParams.gitCredentialsId, url: stepParams.gitUrl]]
    )
}
