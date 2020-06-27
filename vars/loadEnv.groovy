
/*def call(path) {
    properties = new Properties()
    File propertiesFile = new File(path)
    properties.load(propertiesFile.newDataInputStream())
    Set<Object> keys = properties.keySet();
    for(Object k:keys){
    String key = (String)k;
    String value =(String) properties.getProperty(key)
    env."${key}" = "${value}"
    }
}*/

def call(Map config) {
  String configPath = config.envFile ? config.envFile : "${env.WORKSPACE}/staging.properties"
  configFile = readFile configPath
  writeFile(file: 'script', text: configFile)
  
  properties = new Properties()
  File propertiesFile = new File(script)
  properties.load(propertiesFile.newDataInputStream())
  Set<Object> keys = properties.keySet();
  for(Object k:keys){
  String key = (String)k;
  String value =(String) properties.getProperty(key)
  env."${key}" = "${value}"
  }
}

/*def call() {
    def filename = "${env.WORKSPACE}/staging.properties"
    echo "DEBUG: loading filename: $filename"
    env_string = readFile filename
    echo "DEBUG: properties for build:\n$env_string"

    Properties props = new Properties()
    props.load(new ByteArrayInputStream(env_string.getBytes()))
    return props
}*/
