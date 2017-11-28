def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()
	def pathList = config.path ?: ['./'];

	def projectName = config.projectName ?: 'ms-sample';

	echo "projectName = ${projectName}";
	echo "pathList = ${pathList}";

	
}
