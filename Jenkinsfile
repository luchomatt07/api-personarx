pipeline {
    agent any

    tools{
	  maven 'Maven Apache'
	}
	
	environment {
		SCANNER_HOME = tool 'sonarscanner' // Name from Jenkins Global Tool Configuration
		SONARQUBE_ENV = 'My SonarQube Server'  // Name from Jenkins Configure System
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
					  -Dsonar.sources=api-personarx/src/main/java/ \
					  -Dsonar.tests=api-personarx/src/test/java \
					  -Dsonar.java.binaries=api-personarx/target/classes \
					  -Dsonar.tests=api-personarx/src/test/java/
					  -Dsonar.junit.reportsPath=api-personarx/target/surefire-reports \
					  -Dsonar.surefire.reportsPath=api-personarx/target/surefire-reports \
					  -Dsonar.jacoco.reportsPath=api-personarx/target/jacoco.exec \
					  -Dsonar.java.coveragePlugin=jacoco \
					  -Dsonar.coverage.jacoco.xmlReportPaths=api-personarx/target/site/jacoco/jacoco.xml
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