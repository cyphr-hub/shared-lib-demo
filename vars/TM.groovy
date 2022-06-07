def call(String user, String pass, String jobName, String projectID, Boolean bool){
    String script = libraryResource('../resources/TM.py')
    writeFile file:'TM.py', text:script
    def output = sh returnStdout:true, script: 'python3 TM.py ' + user + ' ' +  pass + ' "' + jobName + '" ' + projectID + ' ' + bool
    def exists = fileExists 'file'
    if(exists) {
        echo 'Yes'
    }
    return output
}
