node{
    properties([parameters([string(defaultValue: '127.0.0.1', description: 'Please, give IP for container to build', name: 'IP', trim: true), string(defaultValue: 'latest', description: 'What version would like to you to deploy?', name: 'VER', trim: true)])])
    stage ("Remove container"){
        try{
            sh "ssh root@${IP} docker rm -f Flaskex"
        }
        catch(exc){
            sh "echo container deleted"
        }
    }


    stage("Run container"){
        sh "ssh root@${IP} docker run -d --name Flaskex -p 4400:4000 tilenbaevaa/flaskex_centos7:${VER} "
    }
}