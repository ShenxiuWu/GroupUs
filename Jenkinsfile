#!/usr/bin/env groovy

import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL


node{
    try{
        def mvnHome
        mvnHome = tool 'maven 3.5.4'
        
        stage('checkout') {
            git branch: "${env.BRANCH_NAME}", credentialsId: 'd383a1cc-5b34-4b81-a95e-d4d6223354bb', url: 'https://github.com/sw3196/GroupUs.git'
        }

        stage('compling, test, packaging'){
            sh "'${mvnHome}/bin/mvn' clean verify"
        }

        stage('archival'){
            publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site/jacoco/', reportFiles: 'index.html', reportName: 'Code Coverage', reportTitles: ''])

            step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])

            archiveArtifacts 'target/*.?ar'
        }
        stage('return status'){
            setBuildStatus("SUCCESS", 'Build '+currentBuild.displayName+' succeeded in '+currentBuild.durationString)
        }
        stage ('PMD analysis') {
                // step([$class: 'PmdPublisher', failedTotalHigh: '999', failedTotalLow: '999', failedTotalNormal: '999', healthy: '0', pattern: 'build/pmd-results.xml', unHealthy: '999', unstableTotalHigh: '999', unstableTotalLow: '999', unstableTotalNormal: '999'])
                try{

                sh "'${mvnHome}/bin/mvn' -batch-mode -V -U -e pmd:pmd"
                // def pmd = scanForIssues tool: [$class: 'Pmd'], pattern: '**/target/pmd.xml'
                // publishIssues issues:[pmd]

                // publishIssues id:'analysis', name:'White Mountains Issues',
                //    issues:[pmd],
                //    filters:[includePackage('io.jenkins.plugins.analysis.*')]
                }catch(Exception ex){
                    pass
                }
        }
    }catch(Exception ex){
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
        stage('send email & publish report'){
            dropbox configName: 'CI Report Location', remoteDirectory: 'build-${BUILD_NUMBER}', removePrefix: 'target/site', sourceFiles: 'target/site/'
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
