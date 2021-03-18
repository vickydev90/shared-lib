import org.lab.*
    
def call(config, fname){
    String configPath = config ? config : "${env.WORKSPACE}/staging.properties"
    String folderName = fname
    
    def utils = new envbuild()
    utils.envbuild(configPath)
    
    utils.roleAssume(folderName)
}
