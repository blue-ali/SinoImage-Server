package cn.net.sinodata.cm.schedule;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import cn.net.sinodata.cm.init.BpcInitService;
import cn.net.sinodata.framework.log.SinoLogger;

/**
 * 定时服务模块
 * 
 * @author chenwt
 */
public class ScheduledService implements BpcInitService
{
  private static final SinoLogger               logger;   // 日志实例
  private static final long                     delayTime; // 默认延迟时间（即循环调度的间隔时间，单位毫秒）
  private static ArrayList<ScheduleServiceInfo> ssList;   // 定时服务调度列表
                                                           
  // 毫秒换算
  //  private static long aSecondMillis = 1000; // 1秒等于几毫秒
  //  private static long aMinuteMillis = aSecondMillis * 60; // 1分钟等于几毫秒
  //  private static long aHourMillis = aMinuteMillis * 60; // 1小时等于几毫秒
  //  private static long aDayMillis = aHourMillis * 24; // 1天等于几毫秒
  //  private static long aWeekMillis = aDayMillis * 7; // 1周等于几毫秒
  
  // 初始化
  static
  {
    logger = SinoLogger.getLogger( ScheduledService.class );
    delayTime = 10 * 1000;
    ssList = new ArrayList<ScheduleServiceInfo>( );
  }
  
  // 定时服务的类型枚举
  private enum ScheduleType
  {
    PerWeek, PerDay, PerSec
  }
  
  //-----------------------
  // 定时服务信息类
  //-----------------------s
  class ScheduleServiceInfo
  {
    String       scheduledepiction; // 定时服务的描述
    String       schedulename;     // 定时服务的名称
    ScheduleType scheduletype;     // 定时服务的类型
    String       scheduletime;     // 定时服务的时间
    String       scheduleclassname; // 定时服务的类名
    long         lastExcuteTime;   // 上次运行时间
    boolean      run;              // 服务是否正在运行
                                    
    boolean isRun( )
    {
      return run;
    }
  }
  
  /**
   * 初始化线程类
   * 
   * @param cfgPath 定时服务配置文件路径 chenwt
   */
  @SuppressWarnings( "rawtypes" )
  public void initScheduledService( String cfgPath ) throws Exception
  {
    // 加载配置文件中所有的定时服务信息
    Document cfgDoc = new SAXBuilder( ).build( new File( cfgPath ) );
    Element cfgRoot = cfgDoc.getRootElement( );
    List cfgNodes = cfgRoot.getChildren( "scheduleservice" );
    
    // 将上述信息添加到定时服务调度列表
    Iterator it = cfgNodes.iterator( );
    while ( it.hasNext( ) )
    {
      Element cfgNode = (Element)it.next( );
      ScheduleServiceInfo ssInfo = new ScheduleServiceInfo( );
      ssInfo.scheduledepiction = cfgNode.getChildText( "scheduledepiction" );
      ssInfo.schedulename = cfgNode.getChildText( "schedulename" );
      ssInfo.scheduletype = ScheduleType.valueOf( cfgNode.getChildText( "scheduletype" ) );
      ssInfo.scheduletime = cfgNode.getChildText( "scheduletime" );
      ssInfo.scheduleclassname = cfgNode.getChildText( "scheduleclassname" );
      ssInfo.lastExcuteTime = 0;
      ssInfo.run = false;
      ssList.add( ssInfo );
    }
  }
  
  /**
   * 实现SinoDataInitServer接口的execute方法
   */
  public boolean execute()
  {
      new Thread(new Runnable( )
      {
    	 public void run( )
    	 {
    		// 每间隔delayTime毫秒，执行一次启动定时服务的方法
	         while ( true )
	         {
	             StartSchedule( );
	             try {
			         Thread.sleep( delayTime );
			     } catch (InterruptedException e) {
				     logger.info("启动定式服务异常", e);
			     }
	         }
    	 }
      }).start();
      return true;
  }
  
  /**
   * 启动配置好的定时服务线程
   */
  private void StartSchedule( )
  {
    // 日期时间格式
    SimpleDateFormat yyyyMMddHHmmssFmt = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
    
    // 循环调度所有服务
    Iterator<ScheduleServiceInfo> it = ssList.iterator( );
    while ( it.hasNext( ) )
    {
      // 从列表中取得下一个服务
      ScheduleServiceInfo ssInfo = (ScheduleServiceInfo)it.next( );
      
      // 检查服务是否在运行状态，如果服务正在运行，则处理下一个服务
      if ( ssInfo.isRun( ) )
      {
        continue;
      }
      
      // 初始化执行时间
      String lastExcuteTime = yyyyMMddHHmmssFmt.format( ssInfo.lastExcuteTime ); // 格式化的上次执行时间
      String currentTime = yyyyMMddHHmmssFmt.format( System.currentTimeMillis( ) ); // 格式化的当前时间
      
      //---------------------------------------------------------------------
      // 判断调度服务的类型，根据不同类型使用不同的条件判断是否执行定时服务
      // 支持的类型有：
      //     PerWeek - 每周的scheduletime执行一次，scheduletime格式（暂不支持）
      //     PerDay  - 每天的scheduletime执行一次，scheduletime格式（HH:mm）
      //     PerSec  - 每隔scheduletime秒执行一次，scheduletime格式（暂不支持）
      //---------------------------------------------------------------------
      if ( ScheduleType.PerDay == ssInfo.scheduletype )
      {
        // 如果当前日期和上次执行日期相同，表示当天已执行过该服务，继续处理下一个服务
        if ( currentTime.substring( 0, 10 ).equals( lastExcuteTime.substring( 0, 10 ) ) )
        {
          continue;
        }
        
        // 如果当前时间小于计划执行时间，表示服务执行的时间还没有到，继续处理下一个服务
        if ( currentTime.substring( 11, 19 ).compareTo( lastExcuteTime.substring( 11, 19 ) ) < 0 )
        {
          continue;
        }
      }
      else
      {
        // 不支持的类型
        logger.info( "定时服务[" + ssInfo.scheduledepiction + "]的类型[" + ssInfo.scheduletype + "]暂不支持" );
        continue;
      }
      
      // 满足定时服务执行的条件，执行服务
      logger.info( "定时服务[" + ssInfo.scheduledepiction + "]满足启动条件，开始启动" );
      new ScheduleServiceExecuteThread( ssInfo, ssInfo.scheduledepiction ).start( );
    }
  }
  
  //--------------------------------
  // 定时服务执行线程类
  //--------------------------------
  private class ScheduleServiceExecuteThread extends Thread
  {
    
    private final SinoLogger          logger; // 日志实例
    private final ScheduleServiceInfo ssInfo; // 定时服务信息
                                              
    {
      logger = SinoLogger.getLogger( ScheduleServiceExecuteThread.class );
    }
    
    // 构造方法
    public ScheduleServiceExecuteThread( ScheduleServiceInfo ssInfo, String threadName )
    {
      super( threadName );
      this.ssInfo = ssInfo;
    }
    
    // 反射执行定时服务线程
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    public synchronized void run( )
    {
      // 定时服务运行开始，设置为运行状态
      this.ssInfo.run = true;
      try
      {
        // 反射生成定时服务实例
        Class cls = Class.forName( ssInfo.scheduleclassname );
        Object instance = cls.newInstance( );
        // 执行上述生成实例的start方法，启动线程
        Method oMethod = cls.getMethod( "execute" );
        oMethod.invoke( instance );
        // 更新上次执行时间，并记录日志
        ssInfo.lastExcuteTime = System.currentTimeMillis( );
        logger.info( "定时服务[" + ssInfo.scheduledepiction + "]完成" );
      }
      catch ( Exception exc )
      {
        logger.error( "定时服务[" + ssInfo.scheduledepiction + "]异常", exc );
      }
      finally
      {
        // 定时服务运行结束，设置为非运行状态
        ssInfo.run = false;
      }
    }
  }
}
