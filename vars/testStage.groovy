@Grab('org.codehaus.groovy:groovy-astbuilder:3.0.23')
import groovy.transform.Field
import com.levine824.jenkins.configuration.*

@Field String STAGE_NAME = getClass().getName()
@Field String CONFIGURATION_TYPE = 'stage'

def call(Map args = [:]) {
    stage(STAGE_NAME) {
        testStep(this)
    }
}
