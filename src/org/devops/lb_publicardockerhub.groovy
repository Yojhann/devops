package org.devops

def publicarImage(projectGitName) {
    withCredentials([
        usernamePassword(
            credentialsId: "${env.TOKEN_ID}",
            passwordVariable: 'DOCKERHUB_PASSWORD',
            usernameVariable: 'DOCKERHUB_USERNAME'
        )
    ]) {
        sh "docker login -u ${env.DOCKERHUB_USERNAME} -p ${env.DOCKERHUB_PASSWORD}"
        sh "docker tag ${projectGitName} yojhancristancho/${projectGitName}"
        sh "docker push yojhancristancho/${projectGitName}"
    }
}
