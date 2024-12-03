import groovy.transform.Field

@Field String STEP_NAME = getClass().getName()
@Field String RESOURCE_FILE = "notification/notify.tpl"

def call(String recipient, Map args = [:]) {
    script {
        def tplContent = libraryResource RESOURCE_FILE

        def buildStatus = currentBuild.currentResult
        def statusColor = buildStatus == 'SUCCESS' ? 'green' : 'red'
        def headerColor = buildStatus == 'SUCCESS' ? '#28a745' : '#dc3545'

        tplContent = tplContent.replace('${BUILD_STATUS}', buildStatus)
                .replace('${STATUS_COLOR}', statusColor)
                .replace('${HEADER_COLOR}', headerColor)

        writeFile file: "${WORKSPACE}/notify.tpl", text: tplContent
    }

    def email = emailext(
            subject: "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}",
            body: readFile("${WORKSPACE}/notify.tpl"),
            to: recipient,
            mimeType: 'text/html'
    )
}
