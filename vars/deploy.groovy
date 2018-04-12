def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

        def inventoryFile = config.inventoryFile ;
        def playbookFile = config.playbookFile ;
        def extraVars = config.extraVars ;
	
        wrap([$class: 'AnsiColorBuildWrapper', colorMapName: "xterm"]) {        
              ansiblePlaybook installation: 'ansible', inventory: inventoryFile, playbook: playbookFile, colorized:true, extraVars: extraVars
        }

	
}
