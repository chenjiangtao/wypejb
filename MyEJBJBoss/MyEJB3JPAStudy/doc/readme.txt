【关于jboss加载jar的顺序问题的分析与经验总结】
个人经验：
把ejb工程分成四个子工程：业务、接口、po、单元测试
业务工程：如MyEJB3JPAStudy，主要实现业务逻辑
接口工程：如MyEJB3JPAStudyInterface，主要公开remote接口，一共外部调用调用，如果web工程
PO工程：如MyEJB3JPAStudyPo，包含PO，使用注解的方式，等于公开表结构
单元测试：如MyEJB3JPAStudyTest，方便调试业务逻辑

注：
1.其实PO工程的jar不应该公开出去，因为注释的方式会暴露表结构，建议远程调用接口使用Map或自定义个po专门传递参数
2.单元测试功能中需要引入jbossall-client.jar，这个jar应该从服务器端jboss中拷贝出来，如果版本不一致，会出现serialVersionUID不一致的现象

遇到的问题：
除了单元测试功能外，将其他三个工程打包放到jboss目录去启动，发现报错，提示找不到MyEJB3JPAStudyInterface.jar中的类
经过测试发现这与jar包的命名字母顺序有关，将三个jar放在资源管理器中排序得到的结果如下
MyEJB3JPAStudy.jar
MyEJB3JPAStudyInterface.jar
MyEJB3JPAStudyPo.jar
如果jboss是按照字母排序加载jar包，就会出现这个问题，后来做了个测试将文件名改成
0MyEJB3JPAStudyPo.jar
1MyEJB3JPAStudyInterface.jar
2MyEJB3JPAStudy.jar
这样加载就没有问题
说明jboss加载时不能自动寻找所依赖的jar，最后想到一种解决办法，就是jar包中的META-INF\MANIFEST.MF文件
===================================================================
Manifest-Version: 1.0
Class-Path: MyEJB3JPAStudyPo.jar MyEJB3JPAStudyInterface.jar
===================================================================
通过Class-Path项配置依赖jar包，这样不用更改工程名人工改变jar包顺序就可以实现jboss成功加载

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