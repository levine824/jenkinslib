@Grab('org.codehaus.groovy:groovy-astbuilder:3.0.23')
import groovy.transform.Field
import com.levine824.jenkins.config.ConfigHelper


@Field String STEP_NAME = getClass().getName()
@Field Set STAGE_CONFIG_KEYS = ['git']
@Field Set GENERAL_CONFIG_KEYS = ['projectName']

def call(Script script) {
    def testVar = script.testVar
    
    echo "${testVar}"
}