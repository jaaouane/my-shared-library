def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

        def dockerHubId = config.dockerHubId ;
        def projectName = config.projectName ?: 'ms-sample';
        def pathList = config.path ?: ['./'];
        def tag = config.tag ?: 'latest';
        

        for(int i = 0; i < pathList.size(); i++){
	    def targetPath = pathList[i]
	    sh "docker tag ${projectName}/${targetPath}:${tag} ${dockerHubId}/${targetPath}:${tag}";

             docker.withRegistry("https://docker.io/") {
                docker.image("${dockerHubId}/${targetPath}:${tag}").push()
             }
	}

}
