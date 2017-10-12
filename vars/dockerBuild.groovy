def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

	def name = config.name ?: "default";
	def tag = config.tag ?: "1.0";

	sh "git archive HEAD | docker build -t ${name}:${tag} -"
    	return docker.image("${name}:${tag}")
}
