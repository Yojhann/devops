package org.devops

def despliegueContenedor(projectGitName) {
    sh "docker pull ${env.DOCKERHUB_USERNAME}/${projectGitName}"
    sh """ docker run -d --name ${projectGitName} \
    --network=${env.NameNetwork} -p 5174:5174 \
    --user root yojhancristancho/react-test:latest
    """
}

