def call(body) {
	// evaluate the body block, and collect configuration into the object
	def config = [:]
	body.resolveStrategy = Closure.DELEGATE_FIRST
	body.delegate = config
	body()

        def credentialsId = config.gitCredentialsId ;
        
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${credentialsId}", usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD']]) {

           sh("git push https://aymen.jaaouane%40gmail.com:${GIT_PASSWORD}@github.com/jaaouane/ms-sample.git --all")
        }

	
}


  /*
   withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'git-credentials', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD']]) { 
            sh('URL=`git config --get remote.origin.url | sed \"s;://;://${GIT_USERNAME}:${GIT_PASSWORD}@;g\"` && git push --set-upstream ${URL} --all' )    
  }
  
  ou  

  sh "git push origin HEAD:master";

  ou
           
  withCredentials ne marche pas c pourquioi c'est commenté pour contourner le probléme , j'ai fait docker exec sur le conteneur jenkins pour se connecter au conteneur et 
  j'ai lancé la commande git  config credential.helper store  le login et mot de passe sont enregistrés dans le conteneur, ils seront plus demandés 
  */
