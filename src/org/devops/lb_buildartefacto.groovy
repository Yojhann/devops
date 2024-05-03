package org.devops

def install(){
    sh 'npm install'
}

def clone(){
    git branch: "${env.GIT_BRANCH_1}", url: "${env.GIT_URL_1}"
}

