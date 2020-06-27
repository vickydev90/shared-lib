
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
  Map configFile = readFile configPath

  properties = new Properties()
  //File propertiesFile = new File(configFile)
  properties.load(configFile.newDataInputStream())
  Set<Object> keys = properties.keySet();
  for(Object k:keys){
  String key = (String)k;
  String value =(String) properties.getProperty(key)
  env."${key}" = "${value}"
  }
}

/*def call() {
    def filename = readProperties  file: "${env.WORKSPACE}/staging.properties"
    echo "DEBUG: loading filename: $filename"
    env_string = libraryResource filename
    echo "DEBUG: properties for build:\n$env_string"

    Properties props = new Properties()
    props.load(new ByteArrayInputStream(env_string.getBytes()))
    return props
}*/
