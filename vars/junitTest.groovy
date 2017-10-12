def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

	def pathList = config.path ?: ['./'];
	def ignoreFailure = config.ignoreFailure ?: true;

	for(int i = 0; i < pathList.size(); i++){
		def targetPath = pathList[i]

		withMaven(maven: 'maven3'){  // Run the maven build
			sh "cd ./${targetPath} && mvn test -Dmaven.test.failure.ignore=${ignoreFailure}"  }
		// Recup des résultats de TU
		// junit '**/target/surefire-reports/TEST-*.xml'

	}
}
