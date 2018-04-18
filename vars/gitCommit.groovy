def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

        def commitMessage = config.commitMessage ;

        sh "git config user.email 'jenkins@ajconsulting.com' "
        sh "git config user.name 'jenkins' "
        
        sh "git commit -am '${commitMessage}' ";

	
}

