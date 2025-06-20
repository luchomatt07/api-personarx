pipeline {
    agent any

    tools{
	  maven 'Maven Apache'
	}
	
	environment {
		SCANNER_HOME = tool 'sonarscanner' // Name from Jenkins Global Tool Configuration
		SONARQUBE_ENV = 'sonarqube'  // Name from Jenkins Configure System
    }
    stages {
        stage('Checkout') {
            steps {
                // Clonar el repositorio desde GitHub
                git url: 'https://github.com/luchomatt07/api-personarx.git', branch: 'main'
            }
        }
		stage('SonarQube Analysis') {
		  steps {
				withSonarQubeEnv("${env.SONARQUBE_ENV}") {
				  sh """
					${SCANNER_HOME}/bin/sonar-scanner \
					  -Dsonar.projectKey=api-personarx \
					  -Dsonar.sources=src/main/java/ \
					  -Dsonar.tests=src/test/java \
					  -Dsonar.java.binaries=target/classes \
					  -Dsonar.tests=src/test/java/
					  -Dsonar.junit.reportsPath=target/surefire-reports \
					  -Dsonar.surefire.reportsPath=target/surefire-reports \
					  -Dsonar.jacoco.reportsPath=target/jacoco.exec \
					  -Dsonar.java.coveragePlugin=jacoco \
					  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
				  """
				}
		   }
        }
	
        stage('Build') {
            steps {
                // Compilar el proyecto usando Maven
                sh 'mvn clean install'
            }
        }
    }
    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}