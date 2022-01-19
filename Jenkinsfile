def secrets = [
    [path: 'secret/pipelines/ms-config-server', engineVersion: 2, secretValues: [
        [envVar: 'VAULT_HOST', vaultKey: 'VAULT_HOST'],
        [envVar: 'VAULT_PORT', vaultKey: 'VAULT_PORT'],
        [envVar: 'VAULT_TOKEN', vaultKey: 'VAULT_TOKEN']]],
]

pipeline {
    agent {
        docker {
            image 'maven:3.8.2-adoptopenjdk-11'
            args '-v $HOME/.m2:/root/.m2 -e MAVEN_OPTS="-Xms256m -Xmx256m" -e SUREFIRE_OPTS="-Xms512m -Xmx512m"'
        }
    }
    stages {
        stage('Prepare') {
            steps {
                sh 'echo VAULT_HOST=$VAULT_HOST'
                sh 'echo VAULT_PORT=$VAULT_PORT'
                sh 'echo VAULT_TOKEN=$VAULT_TOKEN'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile -ntp -B'
            }
        }
        stage('Unit Tests and Coverage Report') {
            steps {
                withVault([vaultSecrets: secrets]) {
                    sh 'mvn test -ntp -B'
                }
            }
        }
        stage('Quality Gate Check') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {
                        sh 'mvn sonar:sonar -ntp -B'
                    }
                    timeout(time: 15, unit: "MINUTES") {
                        def qualityGate = waitForQualityGate()
                        if (qualityGate.status != 'OK') {
                            error "Pipeline aborted due to non-compliance with Quality Gate"
                        }
                    }
                }
            }
        }
    }
}
