def call(String user, String pass, String jobName, String projectID, Boolean bool){
    script = libraryResource '../resources/TM.py'
    writeFile file:'TM.py', text:script
    sh('echo "Here"')
    sh "python3 TM.py ${user} ${pass} \"${jobName}\" ${projectID} ${bool}"
}
