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
  String configPath = config.configFile ? config.configFile : "${env.WORKSPACE}/staging.properties"
  properties = new Properties()
  File propertiesFile = new File(configPath)
  properties.load(propertiesFile.newDataInputStream())
  Set<Object> keys = properties.keySet();
  for(Object k:keys){
  String key = (String)k;
  String value =(String) properties.getProperty(key)
  env."${key}" = "${value}"
  }
}

