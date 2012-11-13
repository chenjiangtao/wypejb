【自定义bean名称】
session bean实现类注释规范
===================================================================================
@Stateless(name="ReceiveParamServiceBean")	//远程调用时ctx.lookup("ReceiveParamServiceBean/remote")
@Remote(ReceiveParamService.class)			//可以放到ReceiveParamService类中，但是为了不暴露信息，建议放到实现类
public class ReceiveParamBean implements ReceiveParamService{
===================================================================================

【EJB远程调用参数规划】
为了po信息的安全行，一般不对外公开po类，remote请求使用map来传递接口，这里封装两个类用于传递接口
RequestMsg
ResponseMsg
这两个类中各自都包含一个map对象，用于传递具体业务参数

【事务控制】
事务配置一般是在service层bean的方法上配置，但是默认是有事务的，也就是说即是不在方法上加注释
@TransactionAttribute(TransactionAttributeType.REQUIRED)
也是可以的，如果想不使用事务，则需要这样配置
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)

以下是事务配置参数：只研究了其中的两个：REQUIRED、NOT_SUPPORTED，其他待研究
1.REQUIRED
2.MANDATORY
3.REQUIRESNEW
4.SUPPORTS
5.NOT_SUPPORTED
6.Never

【关于分包引起的进一步问题】
因为po类没有放到业务工程中，在ejb持久化的是时候出现无法识别po的现象报错
Unknown entity: com.my.model.Order
所以现在改成po和业务处理类必须放在一起，只把需要暴露的remote接口放在接口工程中

META-INF\MANIFEST.MF加载依赖包的方式依然有效

接口工程中只放remote接口和client与server端都需要的实体类，但是这个实体类不能是需要持久化的po类


【对于Dao只提供local接口，不提供remote】

【db2中char做主键的OneToMany问题】
假设order表中有tradeno是char(16)，trans表中也该字段与之形成外键关联，但是不在表结构中体现
order 1-n trans
当tradeno保存的值不满16位时，数据库中会用空格占位，但是ejb无法实现级联查询，通过测试发现如果16位都沾满的话就可以
个人分析是空格占位引起的，因为第一查询的时候order的po主键值没有空格，但是trans的外键值有空格，ejb中匹配字符串两个值不相等，所以无法查询到trans记录

注：
1.使用char类型作为主键是为了提高效率，varchar开销稍大
2.如果真的使用char类型作为外键进行关联，强烈建议字段长度一定和数据库表设计一致，不能出现不满员的情况让数据库自动填充空格

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