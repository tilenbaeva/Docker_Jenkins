node{
    properties([parameters([string(defaultValue: '165.227.97.205', description: 'Docker Host', name: 'IP', trim: true), string(defaultValue: 'latest', description: 'Version  of the App', name: 'VER', trim: true), string(defaultValue: '4000', description: 'What port would you like to run', name: 'PORT', trim: true)])])
    stage ("Remove container"){
        try{
            sh "ssh root@${IP} docker rm -f Flaskex"
        }
        catch(exc){
            sh "echo container deleted"
        }
    }


    stage("Run container"){
        sh "ssh root@${IP} docker run -d --name Flaskex -p  ${PORT}:4000 tilenbaevaa/flaskex_centos7:${VER} "
    }
}