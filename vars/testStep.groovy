@Grab('org.codehaus.groovy:groovy-astbuilder:3.0.23')
import groovy.transform.Field

@Field String STEP_NAME = getClass().getName()

def call(args) {
    def sc = args[script]
    echo "sc:${sc}"
    echo "${sc.env.test}"
}
