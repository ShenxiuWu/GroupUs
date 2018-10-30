#!/usr/bin/env groovy

import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL


node{
    def mvnHome
    mvnHome = tool 'maven 3.5.4'
    
    try{
    def mvnHome
    mvnHome = tool 'maven 3.5.4'

    stage('compling, test, packaging'){
        sh "'${mvnHome}/bin/mvn' clean verify"
    }
    stage('archival'){
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site/jacoco/', reportFiles: 'index.html', reportName: 'Code Coverage', reportTitles: ''])

        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])

        archiveArtifacts 'target/*.?ar'
    }
    stage('return status, publish report'){
        setBuildStatus("SUCCESS", 'Build '+currentBuild.displayName+' succeeded in '+currentBuild.durationString)
        dropbox configName: 'CI Report Location', remoteDirectory: 'build-${BUILD_NUMBER}', removePrefix: 'target/site/jacoco', sourceFiles: 'target/site/jacoco/'
    }
    }
    catch(Exception ex){
        script {
            properties([[$class: 'GithubProjectProperty',
            projectUrlStr: 'https://github.com/sw3196/GroupUs']])
        }
        step([$class: 'GitHubIssueNotifier',
        issueAppend: true,
        issueLabel: '',
        issueRepo: 'https://github.com/sw3196/GroupUs',
        issueTitle: '$JOB_NAME $BUILD_DISPLAY_NAME failed'])

        setBuildStatus("FAILURE", "some error happened")
    }finally{
        stage('send email'){
            notify('finished')
        }
    }

}


def notify(status){
    emailext (
      to: "wushenxiu@gmail.com",
      subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
    )
}

def setBuildStatus(state, message){
    step([
            $class: 'GitHubCommitStatusSetter',
            reposSource: [$class: "ManuallyEnteredRepositorySource", url: getRepoURL()],
            commitShaSource: [$class: "ManuallyEnteredShaSource", sha: getCommitSha()],
            errorHandlers: [[$class: 'ShallowAnyErrorHandler']],
            statusResultSource: [
            $class: 'ConditionalStatusResultSource',
            results: [[$class: "AnyBuildResult", state: state, message: message]]
            ]
        ])
}

def getRepoURL() {
  sh "git config --get remote.origin.url > .git/remote-url"
  return readFile(".git/remote-url").trim()
}

def getCommitSha() {
  sh "git rev-parse HEAD > .git/current-commit"
  return readFile(".git/current-commit").trim()
}
