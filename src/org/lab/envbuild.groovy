package org.lab

def envbuild(configPath) {
  def props = readProperties  file: configPath
  keys= props.keySet()
  for(key in keys) {
      value = props["${key}"]
      env."${key}" = "${value}"
  }
}
