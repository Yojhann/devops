def call(){

    pipeline{

        agent any

        tools{
            nodejs 'NodeJS18'
        }

        // triggers {
        //     pollSCM('* * * * *') // Programa la verificación del repositorio cada minuto
        // }

        environment{
            projectName = "${env.GIT_URL_1}".replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
        }

        stages{
            stage('Proceso Construccion') {
                steps {
                    script {
                        def buildapp = new org.devops.lb_buildartefacto()
                        buildapp.install()
                        def cloneapp = new org.devops.lb_buildartefacto()
                        cloneapp.clone()
                    }
                }
            }

            stage('Analisis Sonarqube ') {
                steps {
                    script {
                        def test = new org.devops.lb_analisissonarqube()
                        test.runTest()
                        def analisysSonarqube = new org.devops.lb_analisissonarqube()
                        analisysSonarqube.analisys("${projectName}")
                    }
                }
            }
        }
    }
}
