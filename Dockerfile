
FROM centos:centos7
MAINTAINER "tilenbaevaa@gmail.com"

COPY .   /app/
WORKDIR /app

EXPOSE 4000
RUN yum install epel-release -y
RUN yum install python-pip -y
RUN pip install -r /app/app/requirements.txt

ENTRYPOINT [ "python", "/app/app/app.py" ]
