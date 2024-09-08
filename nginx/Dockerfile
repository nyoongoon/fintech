FROM nginx:latest
# 작성된 conf파일 복사
COPY default.conf /etc/nginx/conf.d/default.conf
# 도커에서 실행할 명령어
CMD ["nginx", "-g", "daemon off;"]