def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

        def projectName = config.projectName ?: 'ms-sample';
        def pathList = config.path ?: ['./'];
        def imgVersion = config.imgVersion ?: 'latest';
       
        echo "imgVersion dans lib groovy =${imgVersion}"

        for(int i = 0; i < pathList.size(); i++) {
                def targetPath = pathList[i]
		sh "sed -i 's;${projectName}/${targetPath}:.*;${projectName}/${targetPath}:${imgVersion};g' docker-compose.yml";
        }

	
}
