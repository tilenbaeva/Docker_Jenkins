node {
    properties([parameters([string(defaultValue: '127.0.0.1', description: 'Please, provide an IP to host a Website', name: 'IP', trim: false)])])
    stage("Install git"){
        sh "ssh ec2-user@${IP}    sudo yum install git python-pip -y"
    }
     stage("Clone a repo"){
        git "git@github.com:tilenbaeva/Flaskex.git"
    }
    stage("Run App"){
        try{
        sh "ssh ec2-user@${IP}  sudo mkdir /flaskex  2> /dev/null"
        }
        catch(exc){
            sh "echo folder exists"
        }
    }  
    stage("Copy files"){
        sh "scp -r * ec2-user@${IP}:/home/ec2-user"
    }
    stage("Move files to /flask"){
        try{
        sh "ssh ec2-user@${IP}   sudo mv /home/ec2-user/*   /flaskex/"
        }
        catch(exc){
            sh "echo files/folder exists"
        }
    }
    stage("Install requirements"){
        sh "ssh ec2-user@${IP}     sudo  pip install -r /flaskex/requirements.txt"
    }
    stage("Move service to /ect"){
        sh "ssh ec2-user@${IP}     sudo mv /flaskex/flaskex.service    /etc/systemd/system"
    }
    stage("Start service"){
        sh "ssh ec2-user@${IP}    sudo systemctl start flaskex"
    }
}