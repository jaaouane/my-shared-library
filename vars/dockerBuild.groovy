def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

	def projectName = config.projectName ?: 'ms-sample';
        def pathList = config.path ?: ['./'];
        def imgVersion = config.imgVersion ?: 'latest';
        def isLatest = config.isLatest ?: true;
	


        for(int i = 0; i < pathList.size(); i++){
		def targetPath = pathList[i]
                docker.build("${projectName}/${targetPath}:${imgVersion}","./${targetPath}")
                if(isLatest) {
	           sh "docker tag ${projectName}/${targetPath}:${imgVersion} ${projectName}/${targetPath}:latest"
	        }
        }

	
}
