@Library('shared-lib-demo') _

pipeline {
	agent any
	
	parameters {
		booleanParam(name: 'ValidateThreats', defaultValue: false, description: 'Perform ThreatModeler Threats Validation.')
	}
	
	environment { 
            CREDS = credentials('TMcreds') 
	    def config = readJSON file: 'resources/TM.json'
            projectId = "${config."$JOB_NAME"}"
        }
	stages {
    
    
        stage('Building Pilot App') {
            steps {
                sh 'exit 0'
            }
        }
        
        stage('Threat Model Validation') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {    
		script {
			Boolean bool = params.ValidateThreats
			
			if(projectId=="null") {
			    currentBuild.result = 'FAILURE'
			    println "ThreatModeler ProjectId not found in JSON."
			    return
			}
			
			def response = TM(CREDS_USR, CREDS_PSW, JOB_NAME, projectId, bool)
			
                	sh '''#!/bin/bash -l
			
                	approvalResponse=$(echo $(echo "'''+response+'''" | cut -d "\n" -f 1))
			threatsResponse=$(echo $(echo "'''+response+'''" | cut -d "\n" -f 2))
			
                	approvalSuccessful="ThreatModeler Approval Validation Successful."
			threatsDetected="Threats Detected in ThreatModeler"
                	
			echo $approvalResponse
			
			statusCode=0
			
                 	if [[ $approvalResponse != $approvalSuccessful ]]
			then
                    		statusCode=1
                 	fi
			
			if [[ $threatsResponse != "" ]]
			then
				echo $threatsResponse
				if [[ $threatsResponse == $threatsDetected ]]
				then
					statusCode=1
				fi
			fi
			
			exit $statusCode
                	'''
		  }
                }
            }
        }
	
      stage('Push') {
            steps {
                sh 'exit 0'
            }
        }
    
    }
} 
