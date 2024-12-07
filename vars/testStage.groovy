@Grab('org.codehaus.groovy:groovy-astbuilder:3.0.23')
import groovy.transform.Field

@Field String STAGE_NAME = getClass().getName()
@Field String CONFIGURATION_TYPE = 'stage'

def call(Map args = [:]) {
    stage(STAGE_NAME) {
        agent $ { args.test }
        steps {
            script {
                echo "stageName:${STAGE_NAME}"
            }
        }

    }
}
