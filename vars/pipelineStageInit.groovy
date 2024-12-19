import com.levine824.jenkins.config.ConfigLoader
import groovy.transform.Field

@Field String STEP_NAME = getClass().getName()

def call(Map args = [:]) {
    def yaml = args.configFile ? (readFile args.configFile) : (libraryResource 'config.yml')
    def loader = ConfigLoader.load(yaml)
}
