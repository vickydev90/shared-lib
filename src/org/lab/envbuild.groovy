package org.lab

def envbuild(config) {
  def props = readProperties  file: configPath
  keys= props.keySet()
  for(key in keys) {
      value = props["${key}"]
      env."${key}" = "${value}"
  }
}
