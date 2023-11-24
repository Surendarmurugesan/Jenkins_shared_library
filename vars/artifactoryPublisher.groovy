def call(credentialsId) {
    // def rtCliHome = tool 'jf' // Specify the tool name defined in Jenkins

    // // Make sure the JFrog CLI is in the PATH
    // env.PATH = "${rtCliHome}/bin:${env.PATH}"

    // Artifactory details
    def artifactory_URL = 'http://localhost:8082/artifactory/'
    def artifactoryRepo = 'example-repo-local'
    def artifactoryPath = 'kubernetes/' // The path in Artifactory where you want to store the JAR

    // Retrieve Artifactory username and password from Jenkins credentials
    withCredentials([usernamePassword(credentialsId: credentialsId, passwordVariable: 'ARTIFACTORY_PASSWORD', usernameVariable: 'ARTIFACTORY_USERNAME')]) {
        // Publish to Artifactory
        sh "jf rt u --url=${artifactory_URL} --user=${ARTIFACTORY_USERNAME} --password=${ARTIFACTORY_PASSWORD} target/kubernetes-*.jar ${artifactoryRepo}/${artifactoryPath}"
    }
}