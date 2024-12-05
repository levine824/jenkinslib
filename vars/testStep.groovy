@Grab('org.codehaus.groovy:groovy-astbuilder:3.0.23')
import groovy.transform.Field

@Field String STEP_NAME = getClass().getName()

def call(Map args = [:]) {
    echo this.STEP_NAME
}
