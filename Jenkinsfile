pipeline {
    agent any

    tools{
	  maven 'Maven Apache'
	}
    stages {
	    stage('Checkout') {
            steps {
               git url: 'https://github.com/luchomatt07/api-personarx.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}