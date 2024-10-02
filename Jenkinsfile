pipeline {
    agent any

    tools {
        git 'Default'
        jdk 'JAVA_HOME'
        maven 'M2_HOME'

    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'Wael-BR', url: 'https://github.com/Wael-BR/Devops.git'
            }
        }

        stage('Environment Validation') {
            steps {
                sh 'echo JAVA_HOME=$JAVA_HOME'
                sh 'echo M2_HOME=$M2_HOME'
            }
        }

        //stage('Compile Stage') {
        //    steps {
        //        // Use Maven to clean and compile the project
        //        sh 'mvn clean compile'
        //    }
        //}
    }

    post {
        always {
            cleanWs()
        }
    }
}