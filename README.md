# qGraph

## greenplum docker配置
1 在命令行中执行
```
docker run -it -h=gpdbsne -p 5432:5432 -p 88:22 kochanpivotal/gpdb5oss bin/bash
```
2 进入docker容器后，默认greenplum数据库是没有启动的，可以使用startGPDB.sh和stopGPDB.sh启停数据库
```
startGPDB.sh
```
3 数据库启动后，默认用户是gpadmin，默认数据库也是gpadmin，需要修改gpadmin的密码
```
su - gpadmin
plsql
alter user gpadmin with password 'TRtest456';
```