# 打包镜像命令
# docker build -t exam-service -f docker/Dockerfile .

FROM openjdk:8-jdk-alpine
LABEL maintainer="zuoxin.ning"
WORKDIR /opt/app
COPY docker/* .
COPY target/exam-ning-springcloud-system-exam.jar .

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=docker

CMD ["sh", "-x", "run.sh"]