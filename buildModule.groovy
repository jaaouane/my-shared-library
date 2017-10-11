def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()
	def pathList = config.path ?: ['./'];

	def buildProfile = config.buildProfile ?: 'prod';

	for(int i = 0; i < pathList.size(); i++){
		def targetPath = pathList[i]

		// Appel de MAVEN PACKAGE
		withMaven(maven: 'maven3'){
			// Run the maven build
			sh "cd ${targetPath} && mvn -P${buildProfile} -Dmaven.test.skip=true clean package"
		}
		
	}
}
