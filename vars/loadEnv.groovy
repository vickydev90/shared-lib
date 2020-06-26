package com.jenkins.library

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
}
def call() {
  String configPath = "${env.WORKSPACE}/staging.properties"
  properties = new Properties()
  File propertiesFile = new File(configPath)
  properties.load(propertiesFile.newDataInputStream())
  Set<Object> keys = properties.keySet();
  for(Object k:keys){
  String key = (String)k;
  String value =(String) properties.getProperty(key)
  env."${key}" = "${value}"
  }
}*/

def call() {
    def filename = readFile "${env.WORKSPACE}/staging.properties"
    echo "DEBUG: loading filename: $filename"
    env_string = libraryResource filename
    echo "DEBUG: properties for build:\n$env_string"

    Properties props = new Properties()
    props.load(new ByteArrayInputStream(env_string.getBytes()))
    return props
}
