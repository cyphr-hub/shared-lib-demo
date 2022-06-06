def call(String user, String pass, String jobName, String projectID, Boolean bool){
    String script = libraryResource('../resources/TM.py')
    sh "python3 -c '${script}' ${user} ${pass} "$jobName" ${projectID} ${bool}"
}
