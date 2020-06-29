def call(config){
    String configPath = config ? config : "${env.WORKSPACE}/staging.properties"
    def props = readProperties  file: configPath
    keys= props.keySet()
    for(key in keys) {
        value = props["${key}"]
        env."${key}" = "${value}"
    }
}