
 ```bash
 # 创建docker
 docker build -t hello_deocker . 
 ```

```bash
#查看所有的镜像,下面两者都可
 docker images
 docker image ls

 # 运行镜像 docker run 镜像名字
 docker run hello_docker
 ```

 其他操作
 ```bash
 # 列出正在运行的容器
 docker ps

 # 列出所有的容器
docker ps -a

# 停止容器
docker stop <容器ID>

# 列出所有的镜像
docker images

# 修改镜像名字 docker tag <旧镜像名称> <新镜像名称> Docker要求镜像的仓库名称必须是小写字母格式
docker tag old_image new_image
 ```

上传到dockerhub

```bash
# 使用Docker hub账号在验证本地登录
docker login

# 看看本地的镜像
docker images

# 查看容器ID
docker ps -a

# docker commit 容器ID hello_docker
docker commit b0d725baf193 hello_docker

# docker tag 镜像ID 用户名称/镜像源名(repository name):新的标签名(tag)
docker tag hello_docker wu1351658806/hello_docker 

# docker push <hub-user>/<repo-name>:<tag>
docker push wu1351658806/hello_docker
```

[play with docker](https://labs.play-with-docker.com/)可以免费尝试各种docker镜像


## 错误
macOS 构建的镜像在 linux 系统运行的时候出现以下错误:
        $ docker run wu1351658806/hello_docker
        WARNING: The requested image's platform (linux/arm64/v8) does not match the detected host platform (linux/amd64/v3) and no specific platform was requested
        exec /usr/local/bin/docker-entrypoint.sh: exec format error

解决：
1. 删除之前创建的镜像容器
2. 使用 BuildKit 进行多平台构建，使用 --platform 选项来指定构建的目标平台为 linux/amd64，使用 -t 选项给镜像指定一个标签名称，并将 . 作为构建上下文路径。
```bash
docker buildx build --platform linux/amd64 -t hello_docker .
docker images
docker ps -a
docker commit 容器ID REPOSITORY
docker push username/REPOSITORY_NAME 
```
3. 进入[play with docker](https://labs.play-with-docker.com/)，重新pull仓库
```bash
docker pull username/REPOSITORY_NAME 
docker images
docker run
```
ps: 用buildx创建后本地会出现`WARNING: The requested image's platform (linux/amd64) does not match the detected host platform (linux/arm64/v8) and no specific platform was requested`, 但是不影响结果的展示。

### 其他常用操作
```bash
# 停止所有的容器
docker ps -aq

# 停止所有的容器
docker stop $(docker ps -aq)

# 删除所有的容器
docker rm $(docker ps -aq)

 # 删除所有停止的容器
docker containerdocker ps -aq prune -f

# 删除所有的镜像
docker rmi $(docker images -q)
```