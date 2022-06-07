def call(String user, String pass, String jobName, String projectID, Boolean bool){
    String script = libraryResource('../resources/TM.py')
    writeFile file:'TM.py', text:script
    response = sh "python3 TM.py ${user} ${pass} \"${jobName}\" ${projectID} ${bool}"
    return response
}
