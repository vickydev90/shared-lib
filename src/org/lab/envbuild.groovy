package org.lab

def envbuild(configPath) {
  def props = readProperties  file: configPath
  keys= props.keySet()
  for(key in keys) {
      value = props["${key}"]
      env."${key}" = "${value}"
  }
}

def roleAssume(folderName) {
  switch(folderName) {
    case "sandbox":
        result = "${sandbox}"
        break
    case "prod":
        result = "${prod}"
        break
    case "nonprod":
        result = "sup"
        break
    default:
        result = "def"
        break
    }
  echo "${result}"
  withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'awstest-creds', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
    sh "echo ${AWS_ACCESS_KEY_ID}"
    sh "echo ${AWS_SECRET_ACCESS_KEY}"
    def val = sh (script: "./test.sh", returnStdout: true) 
    echo "${val}"
    }
  
}
