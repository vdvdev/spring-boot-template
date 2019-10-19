# Template spring-boot

###Liquibase

Schema, user, password (from configuration) should be created before liquibase run.
```
CREATE DATABASE IF NOT EXISTS TEMPLATEDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS LIQUIBASE_TEMPLATEDB;
GRANT ALL ON TEMPLATEDB.* TO LIQUIBASE_TEMPLATEDB@'%' IDENTIFIED BY 'password';
CREATE USER IF NOT EXISTS TEMPLATEDB;
GRANT SELECT, UPDATE, INSERT, DELETE, SHOW VIEW ON TEMPLATEDB.* TO TEMPLATEDB@'%' IDENTIFIED BY 'password';
FLUSH PRIVILEGES;
```

###Docker
docker run --name demo-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7
docker exec -i -t demo-mysql /bin/sh
docker rmi $(docker images | grep 'startnames')

docker build -f CustomDockerfile -t myimage:1.0.0 .
docker run --name myimage \
-v c:/Users/process_engine/src/main/resources/processengine.properties:/etc/configs/processengine.properties \
-v c:/Users/configs/V2:/etc/configs/V2 \
-v c:/Users/process_engine/resources:/etc/configs/transformation-specs -p 8080:8080 -d myimage:1.0.0

###helm k8
docker build -t registry.com/spring-boot-template/init_branch:{{git.version}} .
or
mvn compile jib:dockerBuild

after installing helm & k8
helm init

helm install stable/nginx-ingress --name my-nginx --namespace nginx-ingress

To run helm charts from Core Platform projects corresponding secret should be created to access Nexus docker registry (secret name is the same for all projects):
kubectl create secret docker-registry docker-registry-read --docker-server=registry.server.cloud --docker-username=registry_user --docker-password=registry_password --docker-email=user#email.com

kubectl delete -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
kubectl create -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
kubectl proxy
kubectl -n kube-system get secret |grep  kubernetes-dashboard-token |cut -f1 -d ' ' |  xargs kubectl -n kube-system describe  secret
or
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep admin-user | awk '{print $1}')
http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/

kubectl port-forward cp-athena-service-deployment-75489cb9b-s9xw9 9001:8080
kubectl delete pod cp-gdpr-service-deployment-6cf984cd6f-mxwzk --grace-period=0 --force

helm upgrade -i spring-boot-template src/helm/charts/spring-boot-template/ -f local-values.yaml