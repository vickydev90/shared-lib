import org.lab.*
    
def call(config){
    String configPath = config ? config : "${env.WORKSPACE}/staging.properties"
    
    def utils = new envbuild(this)
    utils.envbuild(configPath)
}
