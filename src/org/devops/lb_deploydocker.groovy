package org.devops

def despliegueContenedor (projectGitName) {
    sh "docker pull yojhancristancho/react-test-jenkinsfile"
    sh """ docker run -d --name ${projectGitName} \
    --network=${env.NameNetwork} -p 5174:5174 \
    --user root yojhancristancho/${projectGitName}
    """
}
