@Grab('org.codehaus.groovy:groovy-astbuilder:3.0.23')
import groovy.transform.Field
import com.levine824.jenkins.configuration.*

@Field String STAGE_NAME = getClass().getName()
@Field String CONFIGURATION_TYPE = 'stage'

def call(Map args = [:]) {
    stage(STAGE_NAME) {
        //def str = ConfigurationLoader.forTest()
        agent "${args.test}"
        
        script{
           sh "hostname"
           echo "stageName:${STAGE_NAME}"
           //echo "str:${str}"
        }

    }
}
