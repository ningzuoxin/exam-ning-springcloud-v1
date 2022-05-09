# 打包镜像命令
# docker build -t ${IMAGE} -f deploy/Dockerfile target/exam-ning-springcloud-system-exam
FROM openjdk:8-jdk-alpine
MAINTAINER zxning@qq.com
WORKDIR /opt/app
COPY . .
CMD ["sh", "-x", "run.sh"]