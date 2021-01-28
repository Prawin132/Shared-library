#!/usr/bin/env groovy

def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    new Sample(script:this).code()
    new Sample(script:this).build()
    new Sample(script:this).test()
    echo config.reponame

    pipeline {
        agent any

        tools {
            maven 'maven-3.6.0'
        }

        environment {
            REPONAME = "${config.reponame}"
        }

        stages {
            stage('code') {
                steps {
                    cleanWs()
                    checkout scm
                }
            }

            stage('build') {
                steps {
                    sh 'mvn -B -DskipTests clean package'
                    echo 'Building....'
                }
            }

            stage('test') {
                steps {
                    sh 'mvn test'
                    echo 'Testing...'
                }
            }
        }
    }
}
