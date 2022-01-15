# Linux指令

1. 查看进程
   - `ps -aux | grep java`
2. 结束进程
   - `kill -9 10528`
3. 后台运行进程
   - `nohup java -jar ./ottcard-operator-web-4.5.0-prod.jar --server.port=9058 &`
   - `./start`
4. 查看日志
   - `tail -n500 ottcard.client.web.2021-12-03.log -f`