@Grab('org.codehaus.groovy:groovy-astbuilder:3.0.23')
import groovy.transform.Field

@Field String STEP_NAME = getClass().getName()

def call(Map args = [:]) {
     def script = args[script]
     echo "script:${script}"
     echo "${script.env.test}"
}
