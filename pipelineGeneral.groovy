def call(Map param){

    pipeline{

        agent any

        tools{
            nodejs 'NodeJS18'
        }

        // triggers {
        //     pollSCM('* * * * *') // Programa la verificación del repositorio cada minuto
        // }

        environment{
            PROJECT = "${env.GIT_URL_1}".replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
        }

        stages{
            stage('Proceso Construccion') {
                steps {
                    script {
                        def cloneapp = new org.devops.lb_buildartefacto()
                        cloneapp.clone(scmUrl:params.scmUrl)
                        def buildapp = new org.devops.lb_buildartefacto()
                        buildapp.install
                    }
                }
            }

            stage('Analisis Sonarqube ') {
                steps {
                    script {
                        def test = new org.devops.lb_analisissonarqube()
                        analisisSonar.analisisSonar("$PROJECT")
                    }
                }
            }
        }
    }
}
