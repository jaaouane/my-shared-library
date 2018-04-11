def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

	echo "debug library"
       	
	echo "config.message = ${config.message}" 
	
	def message = config.message ?: 'hello aymen';

        echo "${message}" 
	
}
