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
                        def buildimage new org.devops.lb_buildimagen()
                        buildimage.buildImageDocker("${projectName}")
                    }
                }
            }
            stage('Fase 2: publicar imagen a docker hub') {
                steps {
                    script {
                        def publicImagenew org.devops.lb_publicardockerhub()
                        publicImage.publicarImage("${projectName}")
                    }
                }
            }
            
            stage('Fase 2: Desplegar imagen en docker') {
                steps {
                    script{
                        def deployImg = new org.devops.lb_deploydocker()
                        deployImg.despliegueContenedor ("$(projectName)")
                        }
                    }
                }
            
            stage('Fase 2: Analisis con OWASP') {
                steps {
                    script{
                        def owasp = new org.devops.lb_owasp()
                        owasp.AnalisisOwasp ("${projectName}")
                        }
                    }
                }

        }
    }
}

