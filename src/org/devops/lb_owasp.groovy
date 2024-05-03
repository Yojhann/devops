package org.devops

def Analisisowasp (projectGitName){
    sh """ docker run --rm-v ProjectOwasp:/zap/wrk/:rw \
        --user root --network=${env.NameNetwork} \
        -t owasp/zap2docker-stable \
        zap-full-scan.py \
        -t ${env.dominio} \
        -r ProjectOwasp.html -I
        """
}