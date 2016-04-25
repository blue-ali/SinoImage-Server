package cn.net.sinodata.framework.exception.message;

/** 错误码，对应该错误的抽象类型 */
public class ExceptionIDs {
	
	/** 资源类 */
	public static final int RESOURCE_ERROR = 10001;
	public static final int RESOURCE_CANNOT_BE_FOUND = 10002;
	public static final int URL_CANNOT_BE_CREATED = 10003;
	public static final int THREAD_OPERATION_ERROR = 10004;
	public static final int RESOURCE_CANNOT_BE_INSTANCED = 10005;
	public static final int INVALID_TRANSFORMER = 10006;
	public static final int EXECUTE_COMMAND_ERROR = 10007;		
	
	/** 系统级异常 */
	public static final int INPUT_ERROR = 10102;
	public static final int OUTPUT_ERROR = 10103;
	public static final int AUTHORITY_ERROR = 10104;
	public static final int DB_ERROR = 10105;
	public static final int NULL_OBJECT = 10106;				//对象为空，在需要该对象时，该对象为空则要抛此类异常
	public static final int PROCESS_STATE_ERROR = 10107;
	public static final int CONTEXT_MISSING_ERROR = 10108;
	public static final int TYPE_CHECK_ERROR = 10109;
	public static final int JPDL_PARSE_ERROR = 10110;
	public static final int SUSPEND_OPERATION_ERROR = 10111;
	public static final int JNDI_ERROR = 10112;
	public static final int TYPE_NOT_MATCH = 10113;
	public static final int VALUE_NOT_MATCH = 10124;
	public static final int QUERY_ERROR = 10114;
	public static final int ACTIVITY_QUERY_ERROR = 10115;
	public static final int NULL_TASK = 10116;
	public static final int INVALID_CLASSNAME = 10117;
	public static final int INVALID_TRANSACTION = 10118;
	public static final int FILE_OPERATION_ERROR = 10119;
	public static final int FILE_EXISTS_ERROR = 10120;
	public static final int RESUME_OPERATION_ERROR = 10121;
	public static final int RULE_EXECUTE_ERROR = 10122;	//规则执行异常
	public static final int RULE_RESULT_ERROR = 10123;  //规则结果异常
	public static final int TRANS_EXECUTE_NOMETHOD_ERROR = 10124;  //执行交易无对应方法
	
	/** 服务异常 **/
	public static final int SERVICE_AUTO_TAKE_TASK_ERROR = 30001;
	public static final int SERVICE_COMPLETE_TASK_ERROR = 30002;
	public static final int SERVICE_START_PROCESS_ERROR = 30003;
	public static final int SERVICE_TAKE_TASK_ERROR = 30004;
	public static final int SERVICE_TERMINATE_PROCESS_ERROR = 30005;
	
	/** 流程流转异常 */
	public static final int PE_LOGON_ERROR = 20001;
	public static final int PE_LOGOFF_ERROR = 20002;
	public static final int PE_PROCESS_START_ERROR = 21001;
	public static final int PE_COMMON_NODE_ERROR = 21002;
	
	
	public static final int PE_TASK_LIST_QUERY_ERROR = 22001;
	public static final int PE_TASK_LIST_INBOX_QUERY_ERROR = 22002;
	public static final int PE_TASK_LIST_UNLOCK_QUERY_ERROR = 22003;
	public static final int PE_TASK_COMPLETE_ERROR = 22101;
	public static final int PE_TASK_COMPLETE_NULL_STEP_ERROR = 22102;
	public static final int PE_TASK_TAKE_ERROR = 221023;
	public static final int PE_TASK_TAKE_BY_ID_ERROR = 22104;
	public static final int PE_TASK_LOCK_FROM_QUEUE_ERROR = 22105;
	public static final int PE_TASK_TAKE_AUTO_ERROR = 22106;
	public static final int PE_TASK_RELEASE_ERROR = 22107;
	public static final int PE_TASK_QUERY_ERROR = 22108;
	public static final int PE_TASK_REASSIGN_ERROR = 22112;
	public static final int PE_TASK_QUERY_NULL_ERROR = 22109;
	public static final int PE_PROCESS_SUSPEND_ERROR = 22110;
	public static final int PE_PROCESS_RESUME_ERROR = 22111;
	public static final int PE_TASK_QUERY_FILTER_ERROR = 22113;
	public static final int PE_TERMINATE_PROCESS_ERROR = 22114;
	public static final int PE_TASK_COMPLETE_WITHOUT_TAKE_ERROR = 22115;

	public static final int PE_ROSTER_QUERY_ERROR = 23001;
//	public static final int PROCESS_ERROR = 11001";
//	public static final int DEPLOYMENT_ERROR = "11002";
//	public static final int IMAGE_QUERY_ERROR = 11003;
//	public static final int PROCESS_TRACK_ERROR = 11004;
//	public static final int DECISION_ACTIVITY_ERROR = 11101;
//	public static final int DECISION_HANDLER_ERROR = 11102;
//	public static final int JAVA_ACTIVITY_ERROR = 11103;
//	public static final int JOIN_ACTIVITY_ERROR = 11104;
//	public static final int COMPLETE_TASK_ERROR = 11105;
//	public static final int TASK_COMMIT_ERROR = 12001;
//	public static final int RETRIEVE_TASK_ERROR = 12002;
//	public static final int TASK_ASSIGNED_ERROR = 12003;
//	public static final int PAGE_ERROR = 12004;
//	public static final int TASK_CANDIDATE_ERROR = 12005;
//	public static final int OPERATION_ERROR = 12006;
//	public static final int TASK_ACTIVE_ERROR = 12007;
//	public static final int EXECUTION_EXISTS = 12008;
//	public static final int EXECUTE_ACTIVITY_ERROR = 12009;
//	public static final int START_PROCESS_ERROR = 12010;

	/** LDAP异常 */
	public static final int LDAP_CONNECT_ERROR = 13001;	//LDAP连接异常
	public static final int LDAP_SEARCH_ERROR = 13002;	//LDAP查询异常
	public static final int LDAP_ADD_ERROR = 13003;	//LDAP添加对象异常
	public static final int LDAP_MODIFY_ERROR = 13004;	//LDAP修改对象异常
	public static final int LDAP_DELETE_ERROR = 13005;	//LDAP删除对象异常
	
	
	/** 其他异常 */
	public static final int PROCESS_DEFINITION_ERROR = 15001;
	public static final int TASK_REMINDER_ERROR = 15002;
	public static final int METHOD_ERROR = 15003;
	public static final int INVALID_NAMING = 15004;
	
	protected static Object[][] exceptionIds = { { INPUT_ERROR, "INPUT_ERROR", "输入不正确" },	
	{ OUTPUT_ERROR, "OUTPUT_ERROR", "输出不正确" }, { DB_ERROR, "DB_ERROR", "数据库操作错误" },
	{ AUTHORITY_ERROR, "AUTHORITY_ERROR", "权限错误" }, {NULL_OBJECT, "NULL_OBJECT", "对象为空"},
	{ RESOURCE_ERROR, "RESOURCE_ERROR", "资源错误" },
	{ RESOURCE_CANNOT_BE_FOUND, "RESOURCE_CANNOT_BE_FOUND", "资源无法找到" }, 
	{ JNDI_ERROR, "JNDI_ERROR", "JNDI错误" },
	{ CONTEXT_MISSING_ERROR, "CONTEXT_MISSING_ERROR", "运行上下文缺失" },
	{ TYPE_CHECK_ERROR, "TYPE_CHECK_ERROR", "类型检查异常" },
	{ PROCESS_DEFINITION_ERROR, "PROCESS_DEFINITION_ERROR", "流程定义文件异常" },
	{ JPDL_PARSE_ERROR, "JPDL_PARSE_ERROR", "流程定义解析错误" },
	{ TYPE_NOT_MATCH, "TYPE_NOT_MATCH", "类型匹配错误"},
	{ QUERY_ERROR, "QUERY_ERROR", "查询条件有误"},
	{ ACTIVITY_QUERY_ERROR, "ACTIVITY_QUERY_ERROR", "活动信息记录未查到"},
	{ NULL_TASK, "NULL_TASK", "未找到相应的任务"},
	{ INVALID_CLASSNAME, "INVALID_CLASSNAME", "无效的类名"},
	{ URL_CANNOT_BE_CREATED, "URL_CANNOT_BE_CREATED", "没有找到相符的url"},
	{ INVALID_TRANSACTION, "INVALID_TRANSACTION", "无效的数据库事务对象"},
	{ THREAD_OPERATION_ERROR, "THREAD_OPERATION_ERROR", "线程操作有误"},
	{ RESOURCE_CANNOT_BE_INSTANCED, "RESOURCE_CANNOT_BE_INSTANCED", "资源无法被实例化"},
	{ METHOD_ERROR, "METHOD_ERROR", "方法操作执行有误"},
	{ INVALID_TRANSFORMER, "INVALID_TRANSFORMER", "无效的转化对象"},
	{ FILE_OPERATION_ERROR, "FILE_OPERATION_ERROR", "文件加载失败"},
	{ EXECUTE_COMMAND_ERROR, "EXECUTE_COMMAND_ERROR", "执行命令捕获异常"},
	{ FILE_EXISTS_ERROR, "FILE_EXISTS_ERROR", "未找到相应的文件"},
	{ SUSPEND_OPERATION_ERROR, "SUSPEND_OPERATION_ERROR", "无效的挂起操作"},
	{ RESUME_OPERATION_ERROR, "RESUME_OPERATION_ERROR", "无效的解挂操作"},
	{ INVALID_NAMING, "INVALID_NAMING", "无效的命名"},
	{ VALUE_NOT_MATCH, "VALUE_NOT_MATCH", "所得值与参照值不统一"},
	{ RULE_EXECUTE_ERROR, "RULE_EXECUTE_ERROR", "规则执行异常"},
	{ RULE_RESULT_ERROR, "RULE_RESULT_ERROR", "规则结果不满足规范，请检查是否配置错误" },
	{ TRANS_EXECUTE_NOMETHOD_ERROR, "TRANS_EXECUTE_NOMETHOD_ERROR", "执行交易{0}异常，无对应方法{1}" },
	{ LDAP_CONNECT_ERROR, "LDAP_CONNECT_ERROR", "LDAP连接异常, 连接用户为：{0}"},
	{ LDAP_SEARCH_ERROR, "LDAP_SEARCH_ERROR", "LDAP查询对象异常"},
	{ LDAP_ADD_ERROR, "LDAP_ADD_ERROR", "LDAP添加对象异常"},
	{ LDAP_SEARCH_ERROR, "LDAP_SEARCH_ERROR", "LDAP查询对象异常"},
	{ LDAP_DELETE_ERROR, "LDAP_DELETE_ERROR", "LDAP查询删除异常"},
	{ PE_LOGON_ERROR, "PE_LOGON_ERROR", "FileNet PE：用户[{0}]登录失败"},
	{ PE_LOGOFF_ERROR, "PE_LOGOFF_ERROR", "FileNet PE：用户[{0}]登出失败"},
	{ PE_PROCESS_START_ERROR, "PE_START_PROCESS_ERROR", "FileNet PE：启动流程[{0}]失败"},
	{ PE_ROSTER_QUERY_ERROR, "PE_ROSTER_QUERY_ERROR", "FileNet PE：从Roster[{0}]中查询任务失败，任务ID[{0}]"},
	{ PE_TASK_COMPLETE_ERROR, "PE_TASK_COMPLETE_ERROR", "FileNet PE：任务提交失败，任务ID[{0}]"},
	{ PE_TASK_COMPLETE_NULL_STEP_ERROR, "PE_TASK_COMPLETE_NULL_STEP_ERROR", "FileNet PE：任务提交失败，stepElement为空"},
	{ PE_TASK_COMPLETE_WITHOUT_TAKE_ERROR, "PE_TASK_COMPLETE_WITHOUT_TAKE_ERROR", "FileNet PE：直接任务提交失败"},
	{ PE_TASK_TAKE_ERROR, "PE_TASK_TAKE_ERROR", "FileNet PE：获取任务失败，任务ID[{0}]"},
	{ PE_TASK_LOCK_FROM_QUEUE_ERROR, "PE_TASK_LOCK_FROM_QUEUE_ERROR", "FileNet PE：从队列中查询并锁定待处理任务失败"},
	{ PE_TASK_TAKE_BY_ID_ERROR, "PE_TASK_TAKE_BY_ID_ERROR", "FileNet PE：根据任务ID获取任务失败，任务ID[{0}]"},
	{ PE_TASK_LIST_QUERY_ERROR, "PE_TASK_LIST_QUERY_ERROR", "FileNet PE：从队列[{0}]查询任务列表失败"},
	{ PE_TASK_LIST_INBOX_QUERY_ERROR, "PE_TASK_LIST_INBOX_QUERY_ERROR", "FileNet PE：查询已被用户[{1}]获取的任务列表失败"},
	{ PE_TASK_LIST_UNLOCK_QUERY_ERROR, "PE_TASK_LIST_UNLOCK_QUERY_ERROR", "FileNet PE：从队列[{0}]查询未被用户[{1}]锁定的任务列表失败"},
	{ PE_TASK_TAKE_AUTO_ERROR, "PE_TASK_TAKE_AUTO_ERROR", "FileNet PE：用户[{0}]从队列[{1}]中自动获取任务失败"},
	{ PE_TASK_RELEASE_ERROR, "PE_TASK_RELEASE_ERROR", "FileNet PE：用户[{0}]释放任务失败，任务ID[{1}]"},
	{ PE_TASK_QUERY_ERROR, "PE_TASK_QUERY_ERROR", "查询任务异常，任务id[{0}]"},
	{ PE_TASK_REASSIGN_ERROR, "PE_TASK_REASSIGN_ERROR", "转寄任务异常，任务id[{0}]"},
	{ PE_TASK_QUERY_NULL_ERROR, "PE_TASK_QUERY_NULL_ERROR", "没有查询到任务，任务ID[{0}]"},
	{ PE_PROCESS_SUSPEND_ERROR, "PE_PROCESS_SUSPEND_ERROR", "流程挂起异常"},
	{ PE_PROCESS_RESUME_ERROR, "PE_PROCESS_RESUME_ERROR", "流程解挂异常"},
	{ PE_TASK_QUERY_FILTER_ERROR, "PE_TASK_QUERY_FILTER_ERROR", "创建任务查询Filter异常"},
	{ SERVICE_AUTO_TAKE_TASK_ERROR, "SERVICE_AUTO_TAKE_TASK_ERROR", "自动获取任务异常，任务ID[{0}]"},
	{ SERVICE_COMPLETE_TASK_ERROR, "SERVICE_COMPLETE_TASK_ERROR", "提交任务异常，流水号[{0}]，任务ID[{1}]"},
	{ SERVICE_START_PROCESS_ERROR, "SERVICE_START_PROCESS_ERROR", "发起流程异常，流水号[{0}]，交易码[{1}]"},
	{ SERVICE_TAKE_TASK_ERROR, "SERVICE_TAKE_TASK_ERROR", "获取任务异常，任务ID[{0}]"},
	{ SERVICE_TERMINATE_PROCESS_ERROR, "SERVICE_TERMINATE_PROCESS_ERROR", "终止流程失败，流水号[{0}]，流程实例ID[{1}]"},
	{ PE_COMMON_NODE_ERROR, "PE_COMMON_NODE_ERROR", "公共节点处理异常， 任务ID[{0}],流程实例ID[{1}]"},
	{ PE_TERMINATE_PROCESS_ERROR, "PE_TERMINATE_PROCESS_ERROR", "流程终止异常,该笔任务不存在， 流程实例ID[{0}]"}
	};

}
