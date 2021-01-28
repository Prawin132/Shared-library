pipeline {
    agent any
    
    tools{
        maven 'maven-3.6.0'
    }

    stages{
       
        
        stage('build'){
            steps{
                sh 'mvn -B -DskipTests clean package'
                echo "Building...."
            }
        }

        stage('test'){
            steps{
                sh 'mvn test'
                echo "Testing..."
            }
        }
    }
}
