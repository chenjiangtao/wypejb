【读取jar资源文件少字节－2012-11-17】
我将证书文件打包放入jar，但是通过
in = PropsUtil.class.getClassLoader().getResourceAsStream(keyPath);
in.read(key);
读取时却少了3个字节，以testMer.key.p8文件为例，该文件634个字节，但通过上面的方式读取却只有631剩下3个字节为0
这就造成昨天在进行签名运算时出现签名值不正确，还有就是明文一样，每次签名的值都在变化
解决方式：
1.如果是myeclipse创建的ejb工程，那么发布式选择以文件夹的形式发布
2.如果是非ejb工程，那么使用ant的build.xml文件发布，一样采用文件夹的方式发布
3.如果是以jar形式发布，需要将证书文件手工覆盖jar中的文件，只要做过这个操作，该jar就可以正常使用了

***************************************************************
疑问仍未解决，为什么使用eclipse的ant，以及本地的ant打出jar都都无法正常读取呢？
还有一点就是通过在jboss4.2.2GA和jboss4.2.3GA两个版本的jboss验证结果都是一样，少了3个字节

下面想做一个试验验证一下是这个证书文件的问题，还是读取长度问题
cert/testUmpay.key.p8 					长度：635	读取：640	结果：36, -44, 0, 0, 0, 0, 0, 0, 0, 0]	少了3个字节（640-8=632）
cert/testMer.key.p8 					长度：634	读取：640	结果：30, 0, 0, 0, 0, 0, 0, 0, 0, 0]	少了3个字节（640-9=631）
cert/testUmpay.cert.crt					长度：621	读取：625	结果：44, 0, 0, 0, 0]					正确
cert/testMer.cert.crt 					长度：912	读取：915	结果：45, 10, 0, 0, 0]					正确
cert/9996_UMPAY.key.p8					长度：635	读取：640	结果：81, -84, 0, 0, 0, 0, 0, 0, 0, 0]	少了3个字节（640-8=632）
3566_YiDongShangCheng.key.p8			长度：634	读取：640	结果：-105, 0, 0, 0, 0, 0, 0, 0, 0, 0]	少了3个字节（640-9=631）
3568_GuangDongYiDongShiChangBu.key.p8	长度：634	读取：640	结果：-30, 0, 0, 0, 0, 0, 0, 0, 0, 0]	少了3个字节（640-9=631）
file.txt如何读取都是正确的
***************************************************************
经过测试终于找到原因了
in = PropsUtil.class.getClassLoader().getResourceAsStream(keyPath);
in.read(key);
read时如果返回值不为-1表示未读取完成，公司提供的jar只读取一次，如果一次无法读取整个文件那么证书就不完成，造成签名失败
【修改bug】
工程：MyEJB3JPAStudy
E:\wypsmall\Code\Code_Myeclipse\MyEJB3JPAStudy\src\com\my\utils\SpayMySignUtil.java
工程：UPayService 【经验证我的方式正确，业务可以正常处理】
E:\wypsmall\Code\Code_Myeclipse\UPayService\src\com\umpay\gdupay\util\SpayMySignUtil.java

【未修复次bug】
工程：gdDemo
E:\wypsmall\Code\Code_Myeclipse\gdDemo\src\java\com\umpay\gd\util\MySignUtil.java
E:\wypsmall\Code\Code_Myeclipse\gdDemo\src\java\com\umpay\gd\util\WyPaySignUtil.java
工程：MagPlat
E:\wypsmall\Code\Code_Myeclipse\GdSettle_SVN\MagPlat\src\plat\com\umpay\gdPay\util\SpaySignUtil.java
工程：MixPayService
E:\wypsmall\Code\Code_Myeclipse\GdSettle_SVN\MixPayService\src\com\umpay\mixpay\util\SpaySignUtil.java
***************************************************************

【公司以下jar存在问题】
E:\wypsmall\Code\Code_Myeclipse\MyNetWork\lib\Signuilt.jar
E:\wypsmall\Code\Code_Myeclipse\GdSettle_SVN\MixPayWeb\WebRoot\WEB-INF\lib\sign.jar
E:\wypsmall\Code\Code_Myeclipse\GdSettle_SVN\MixPayWeb\WebRoot\WEB-INF\lib\plweb.jar
工程：MixPayService
E:\wypsmall\Code\Code_Myeclipse\GdSettle_SVN\MixPayService\src\com\umpay\mixpay\util\WbankSignUtil.java
E:\wypsmall\Code\Code_Myeclipse\GdSettle_SVN\MixPayService\src\com\umpay\mixpay\util\WyPaySignUtil.java
工程：MixPayWeb
E:\wypsmall\Code\Code_Myeclipse\GdSettle_SVN\MixPayWeb
有大量这样的写法，只要你搜索SignUtil.genPrivateKey(
in = this.getClass().getClassLoader().getResourceAsStream(platKey);
in.read(key);

【容器加载，使得jboss一起动就加载】
wensheng说要集成quartz，所以暂时不考虑研究

【定时器】
Timer有两种，一种是single-event timers，另一种是interval timers。
single-event timers在它的生命周期中只产生一次timeout
interval timers可以在每经过一段时间间隔后产生一次timeout

文章转载自网管之家：http://www.bitscn.com/pdb/java/200605/22160.html
ejb的定时服务是创建一个timer，设置执行的时间间隔然后定制执行，参考例子MyTimerBean
首先创建一个session bean由于创建和取消定时服务
创建定时服务，需要使用ejb中的TimerService，通过注释的方式注入
@Resource       
private TimerService timerService;
创建时使用
timerService.createTimer(new Date(new Date().getTime()+milliseconds),milliseconds,timerName);
取消时使用（自己编写）
Collection<Timer> timers = timerService.getTimers();
for (Timer timer : timers) {
	if(timerName.equals(timer.getInfo()))
		timer.cancel();
}

详细分析：
当调用createTimer时，jboss会将定时器作为记录插入到【hsqldb】内存数据库并把数据存储于硬盘，通过执行下面的命令，可以查看
这也就是为什么第一次调用createTimer，以后都不需要在调用了，只要jar部署好，jboss一起动，就会开始执行
查看数据库（前提是jboss服务已经停止，否则无法打开）
java -cp E:\wypsmall\Develop\jboss-4.2.2.GA\server\default\lib\hsqldb.jar org.hsqldb.util.DatabaseManager -url jdbc:hsqldb:E:/wypsmall/Develop/jboss-4.2.2.GA/server/default/data/hypersonic/localDB

当调用cancel时，jboss会将改定时器记录从数据库中删除，同样可以通过查看数据库来验证（也可以手工删除，使用sql语句delete）

下面试验两个场景
场景1：连续调用两次createTimer，看看数据库记录是不是插入两条
验证结果：会向数据库插入两条记录，并且两个定时服务都会执行

场景2：我想改变原有定时器执行的时间间隔，应该如何解决
根据目前使用的经验，如果想改变定时器执行时间间隔，只能删除原来的，重新创建

【消息引擎】
http://blog.csdn.net/itm_hadf/article/details/7685398

dbQueue-service.xml拷贝到E:\wypsmall\Develop\jboss-4.2.2.GA\server\default\deploy
首先配置一个队列，然后在创建一个监听器，就可以实现消息驱动了，队列用来保存消息，监听器用来处理消息，完成异步操作
具体请参考MyQueueMessageListener

根据timer经验验证以下消息存放的地方，是不是也是【hsqldb】
使用测试类向服务器发送消息，停止jboss，通过管理器查看hsqldb（select * from jms_messages）发现记录已经保存在数据库中，前提是停止监听
注释掉监听器配置
/*
@MessageDriven(activationConfig = {  
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),  
	@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myQueue"),  
	// 当我们使用的是容器来管理事务的时候，acknowledgeMode这个属性设置也没什么意义，    
	// 这里可以省略掉 自动确认模式，也可以不要，。   
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})  
*/
如果重新启动监听器，然后启动jboss，之前的消息会先被处理

【EJB的集群配置@Clustered，有时间在研究】

【调用本地session bean(local)】
在一个bean使用另一个bean需要使用注解的方式注入，用到（@EJB）
@EJB(beanName="EjbInvokeService") private EjbInvokeLocal ejbInvokeLocal;
@EJB private EjbInvokeLocal ejbInvokeLocal;
这两种方式都可以注入，结果是一样的，前者清晰的给出beanName，后者省略
private也可以省略默认是包内访问

【ContextManager-ThreadLocal使用，有时间详细解释】

【日志配置】
jboss统一有配置文件，所以log4j的配置需要放在jboss-4.2.2.GA\server\default\conf\jboss-log4j.xml进行添加
doc中保存了本地的配置，仅供参考
在本工程中使用两个logger类：MyLogger、MyDetailLogger，区别在于
MyLogger无法打印类名
MyDetailLogger可以打印类名
如何使用见LoggerInterceptor类

【拦截器】
使用类的方式作为拦截器，只需要将新建一个类，定义类似下面的方法，方法名不限，并加上注释即可
public class LoggerInterceptor{
	@AroundInvoke
	public Object interceptorMethod(InvocationContext ctx) throws Exception{

使用的时候只需要在session bean的时候
@Interceptors(LoggerInterceptor.class)
public class ReceiveParamBean implements ReceiveParamService{

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
@TransactionManagement(TransactionManagementType.CONTAINER)默认使用容器管理，虽然是默认值，但是最好将注释写出来
@TransactionManagement(TransactionManagementType.BEAN) bean管理事务，可以控制事务提交的边界，没有研究

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