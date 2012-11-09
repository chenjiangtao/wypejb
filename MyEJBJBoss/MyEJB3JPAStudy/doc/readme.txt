【数据库】
目前学习时使用的是db2数据库，如果回家无法连接，则使用mysql进行调试

【jboss配置】
db2-ds.xml				拷贝到	E:\wypsmall\Develop\jboss-4.2.2.GA\server\default\deploy
db2jcc.jar				拷贝到	E:\wypsmall\Develop\jboss-4.2.2.GA\server\default\lib
db2jcc_license_cu.jar	拷贝到	E:\wypsmall\Develop\jboss-4.2.2.GA\server\default\lib

如果ejb工程中需要引入jar，那么在运行的时候需要将jar包拷贝到E:\wypsmall\Develop\jboss-4.2.2.GA\server\default\lib


【数据库】
jdbc:db2://192.168.1.200:50000/develop
wangyp/12345678

【本地环境】
E:\wypsmall\Develop\jboss-4.2.2.GA

【此工程是参考】
https://wypejb.googlecode.com/svn/trunk/MyEJBJBoss/MyEJBStudy