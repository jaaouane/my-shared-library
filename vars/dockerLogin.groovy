def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

        def credentialsId = config.credentialsId ;
	
        withCredentials([usernamePassword(credentialsId: "${credentialsId}", passwordVariable: 'password', usernameVariable: 'username')]) {
           sh "docker login -u=${username} -p=${password}"
        }

	
}
