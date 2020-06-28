def call() {
    String path = readProperties file: "${env.WORKSPACE}/staging.properties"
    writeFile file: 'stage.properties', text: path
    properties = new Properties()
    String fileContents = new File('stage.properties')
    println fileContents
    //File propertiesFile = new File('stage.properties')
    //properties.load(propertiesFile.newDataInputStream())
    //Set<Object> keys = properties.keySet();
    //for(Object k:keys){
    //String key = (String)k;
    //String value =(String) properties.getProperty(key)
    //env."${key}" = "${value}"
    }

/*def call() {
  //String configPath = config.envFile ? config.envFile : "${env.WORKSPACE}/staging.properties"
  configPath = libraryResource "dev/stage.properties"
  //configFile = readFile configPath
  //writeFile file: 'stage.properties', text: configPath

  properties = new Properties()
  //File propertiesFile = new File(configPath)
  properties.load(configPath.newDataInputStream())
  Set<Object> keys = properties.keySet();
  for(Object k:keys){
    String key = (String)k;
    String value =(String) properties.getProperty(key)
    env."${key}" = "${value}"
  }
}*/

/*def call() {
    def filename = "${env.WORKSPACE}/staging.properties"
    echo "DEBUG: loading filename: $filename"
    env_string = readFile filename
    //echo "DEBUG: properties for build:\n$env_string"

    Properties props = new Properties()
    props.load(new ByteArrayInputStream(env_string.getBytes()))
    return props
}*/