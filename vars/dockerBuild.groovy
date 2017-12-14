def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

	
	echo "config.imgVersion = ${config.imgVersion}" 
	echo "config.path = ${config.path}"
	echo "config.projectName = ${config.projectName}"

        echo "imgVersion = ${imgVersion}" 
        echo "pathList = ${pathList}" 
        echo "projectName = ${projectName}" 

	def projectName = config.projectName ?: 'ms-sample';
        def pathList = config.path ?: ['./'];
        def imgVersion = config.imgVersion ?: 'latest';



        for(int i = 0; i < pathList.size(); i++){
		def targetPath = pathList[i]
                docker.build("${projectName}/${targetPath}:${imgVersion}","./${targetPath}")
        }

	
}
