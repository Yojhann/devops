def call(){
    pipeline{
        agent any
        tools{
            nodejs 'NodeJS18'
        }
        environment{
            projectName = "${env.UrlGitHub}".replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
        }
        stages{
            stage('Fase 2: Construcción de imagen en Docker Desktop') {
                steps {
                    script {
                        def buildimage = new org.devops.lb_buildimagen()
                        buildimage.buildImageDocker("${projectName}")
                    }
                }
            }
            stage('Fase 2: publicar imagen a docker hub') {
                steps {
                    script {
                        def publicImage = new org.devops.lb_publicardockerhub()
                        publicImage.publicarImage("${projectName}")
                    }
                }
            }
            
            stage('Fase 2: Desplegar imagen en docker') {
                steps {
                    script{
                        echo "Project Git Name: ${projectGitName}"
                        def deployImg = new org.devops.lb_deploydocker()
                        deployImg.despliegueContenedor ("${projectName}")
                        }
                    }
                }

        }
    }
}
