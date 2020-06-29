def call(Map args) {
  def containers = [
    containerTemplate(name: 'jnlp', image: 'jenkins/jnlp-slave', ttyEnabled: true)
  ]
  
  def label = "jen-agent-${UUID.randomUUID().toString()}"
  def config = args.envFile
  boolean checkoutSubmodule = args.checkoutSubmodule

  podTemplate(label: label, containers: containers) {
    node(label) {
        stage('Checkout') {
          if(checkoutSubmodule == true) {
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
            userRemoteConfigs: [[credentialsId: 'git-cred', url:'https://github.com/vickydev90/bazel-example-cpp.git']]])
          }
          else {
            checkout scm
          }
        }
        stage('setting env') {
            loadEnv(config)
        }  
        stage('Install') {
              sh "echo ${BAZEL_TOOLS}"
        }
    }
  }
}