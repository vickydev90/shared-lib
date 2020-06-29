def call(Map args) {
  def containers = [
    containerTemplate(name: 'jnlp', image: 'jenkins/jnlp-slave', ttyEnabled: true)
  ]
  
  def label = "jen-agent-${UUID.randomUUID().toString()}"
  def config = args.envFile
  podTemplate(label: label, containers: containers) {
    node(label) {
        stage('Checkout') {
          checkout scm
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