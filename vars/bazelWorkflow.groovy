def call(Map args) {
  def containers = [
    containerTemplate(name: 'jnlp', image: 'jenkins/jnlp-slave', ttyEnabled: true)
  ]
  
  def label = "jen-agent-${UUID.randomUUID().toString()}"
  def config = args
  boolean checkoutSubmodule = args.checkoutSubmodule

  podTemplate(label: label, containers: containers) {
    node(label) {
        stage('🔦 code checkout') {
            checkout scm
        }
        stage('setting env') {
            loadEnv(config)
        }
        stage('🔦 submodule checkout') {
          if (checkoutSubmodule == true) {
            checkout([
            $class: 'GitSCM',
            branches: [[name: '*/master']], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [[
              $class: 'SubmoduleOption', 
              disableSubmodules: false, 
              parentCredentials: true, 
              recursiveSubmodules: true, 
              reference: '', 
              trackingSubmodules: false
            ]], 
            submoduleCfg: [], 
            userRemoteConfigs: [[credentialsId: "${creds}", url: "${submoduleRemote}"]]])
           } else {
               sh "echo 'skipping submodules..'"
           }
        }  
        stage('Install') {
              sh "echo ${BAZEL_TOOLS}"
        }
    }
  }
}
