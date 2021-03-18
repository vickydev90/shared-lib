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
        result = "sandbox"
        break
    case "prod":
        result = "prod"
        break
    case "nonprod":
        result = "sup"
        break
    default:
        result = "def"
        break
    }
echo "${result}"
}
