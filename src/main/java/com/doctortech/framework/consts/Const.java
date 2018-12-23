package com.doctortech.framework.consts;

/**
 * 常量文件
 *
 */
public class Const {
	public final static String LOGIN_URL = "/login.html"; //前台登陆页地址
	public final static String SYS_LOGIN_URL = "/sysLogin.html"; //后台登录页面地址
	public final static int CODE_NO_LOGIN=0;
	public final static String CODE_NO_LOGIN_STR="您未登录,请先登录！";
	public final static int CODE_SUCCESS=1;
	
	public final static String CODE_SUCCESS_STR="操作成功！";
	public final static int CODE_ERROR=-1;
	public final static int CODE_HAVE_APPLY=2;//已经报名
	public final static int CODE_NO_PERMISSION=3;//没有权限
	public final static String CODE_ERROR_STR="操作失败！";
	
	
	public final static String LOGIN_SUCCESS_RETURN__URL="fromUrl"; //登陆成功后跳转的url
	public final static  String SYSTEM_USER_SESSION="current_user";
	public final static  String HASH_ALGORITHM = "SHA-1";
	public final static  int SALT_SIZE = 8;
	public final static  int HASH_INTERATIONS = 1024;
	public final static  int LOGIN_ACCOUNT_LOCKED_HOURS=24;//账号登陆锁定时长,单位:小时
	public final static  int LOGIN_MAX_NUM=5;// 账号登陆连续输入密码最大次数
	public final static  String ENCODING="UTF-8";
	//cookie加密密钥
	public final static  String SECRET_KEY = "j1QasAU@#@FAF124f4981fwf293927#@G%&*@!@#09fapofewowfja@#E*W87%$#!xCZANPOAFhRfo2#209%&12*2WUO3aNBQ12p3olJD2@9zmj%&*(0923Tm07Xdw3joIKDFWA$@F";
	
	public final static String SSO_HTTP_INTERFACE_URL = "http://sso.ifuhua.com.cn/httpInterface"; //sso接口地址，获取ssoUserData信息
	
	public final static long FILE_LIMIT=10*1024*1024;

	

	public final static  String DEFAULT_IMAGE_URL="/front/img/avatar.png";
	
	public final static  String DEFAULT_EXPERT_IMAGE_URL="/upload/avatar/expert.png";
	
	/**
	 * 区域员工
	 */
	public final static String NORMAL_ROLE = "normal";
	/**
	 * 普通用户角色名称
	 */
	public final static String STUFF_ROLE="stuff";
	
	/**
	 * 专家用户角色名称
	 */
	public final static String EXPERT_ROLE="expert";
	/**
	 * 区域管理员角色名称
	 */
	public final static String ADMIN_ROLE="area";
	
	/**
	 * 超级管理员角色名称
	 */
	public final static String SUPERADMIN_ROLE="super";
	
	//索引 名
	public final static String INDEX_PATENT="patent";
	
	public final static String INDEX_POLICY="policy";
	
	//需求索引名
	public final static String  INDEX_REQUIREMENT="requirement";
	
	public final static String  INDEX_REQUIREMENT_TYPE="info";

	
	public final static String INDEX_PATENT_TYPE="info";
	public final static String INDEX_POLICY_TYPE="info";
	
	public final static String INDEX_ACHIEVEMENT="achievement";
	public final static String INDEX_ACHIEVEMENT_TYPE="info";

	public final static String INDEX_PROJECT="project";
	public final static String INDEX_PROJECT_TYPE="info";

	public final static String INDEX_EXPERT="expert";
	public final static String INDEX_EXPERT_TYPE="info";
	
	public final static String INDEX_THINK_TANK="thinktank";
	public final static String INDEX_THINK_TANK_TYPE="info";
	
	public final static String INDEX_TEAM="team";
	public final static String INDEX_TEAM_TYPE="info";

	public final static String INDEX_EXPERT_ADMIN="expertadmin";//后台专家管理
	public final static String INDEX_EXPERT_ADMIN_TYPE="info";
	public final static String REQUIREMENT_CATEGORY_TALENT="人才需求";
	public final static String REQUIREMENT_CATEGORY_PROJECT="项目合作需求";
	public final static String REQUIREMENT_CATEGORY_TECH="技术需求";
	
	public final static String INDEX_COLLECT_CARD="collectcard";//名片收藏夹
	public final static String INDEX_COLLECT_CARD_TYPE="info";

	
	public static final long ACTIVITY_DONGGUAN_TALENT_ID=1;
	public static final long ACTIVITY_DONGGUAN_OTHER_ID=2;
	
	
	public final static String INDEX_ALL_TAG="all_tag";
	public final static String INDEX_COMMON_TYPE="info";
	
	public final static String INDEX_EXPERT_TAG="expert_tag";//专家与标签
	public final static String INDEX_PATENT_TAG="patent_tag";//专利与标签
	public final static String INDEX_POLICY_TAG="policy_tag";//政策与标签
	public final static String INDEX_ACHIEVEMENT_TAG="achievement_tag";//成果与标签
	public final static String INDEX_TEAM_TAG="team_tag";//团队与标签
	
	public final static String INDEX_EXPERT_RESEARCH_DIRECTION="expert_research_direction";//专家与研究方向
	public final static String INDEX_EXPERT_UNIT="expert_unit";//专家与单位
	
	public final static String INDEX_WORK_UNIT="work_unit";//所有工作单位
	
	public final static String INDEX_WP_REQUIREMENT_TYPE="info"; //全流程-需求管理
	
	public final static String  INDEX_WP_REQUIREMENT="wp_requirement"; //全流程-需求管理
	
	/**
	 * 登录验证码
	 */
	public final static String LOGIN_CAPTCHA_CODE="loginCaptchaCode";
	
	/**
	 * 注册验证码
	 */
	public final static String REG_CAPTCHA_CODE="regCaptchaCode";
	
	
	/**
	 * 短信验证码超时时间 30分钟
	 */
	public static final int CAPTCHA_VALID_TIME = 30;
	
	/**
	 * 短信验证码一天最大发送次数
	 */
	public static final int CAPTCHA_TODAY_LIMIT = 20;
	
	public final static String  TEMPLATEID="247489";//注册发送短信模板编号 247489
	
	/**
	 * 忘记密码第一步
	 */
	public final static String FORGETPASSWORD_STEP_ONE="forgetpassword_step_one";
	
	public static final String registerTemp="尊敬的用户【%s】，您的验证码是：%s，请您在%s分钟内正确输入！";
	public static final String forgetTemp="尊敬的用户【%s】，您正在进行找回密码操作，您的验证码是：%s！";
	
	public final static Integer PENDING_PASS=1;//待审
	public final static Integer PASS=2; //审核通过
	public final static Integer UNPASS=3; //审核不通过

	public final static String INDEX_EXPERT_RECOMMEND="index_expert_recommend";//首页维权全家推荐
	
	public final static String INDEX_FINANCIAL_PRODUCTS_RECOMMEND="index_financial_products_recommend";//首页知产金融推荐

	
	public final static String INDEX_LEAGUE_PRODUCT_RECOMMEND="index_league_product_recommend";//首页优质服务联盟产品推荐
	
	
	public final static String TIME_FORMAT1="yyyy-MM-dd";
	public final static String TIME_FORMAT2="yyyy/MM/dd";
	public final static String TIME_FORMAT3="yyyy.MM.dd";
	public final static String TIME_FORMAT4="yyyy年MM月dd日";
	public final static String TIME_FORMAT5="yyyyMMdd";
	public final static String TIME_FORMAT1H="yyyy-MM-dd HH:mm";
	public final static String TIME_FORMAT2H="yyyy/MM/dd HH:mm";
	public final static String TIME_FORMAT3H="yyyy.MM.dd HH:mm";
	public final static String TIME_FORMAT1HE="yyyy-MM-dd HH:mm:ss";
	public final static String TIME_FORMAT3HE="yyyy.MM.dd HH:mm:ss";
	
	
	public final static String SAFE_GUARD_INDEX_EXPERT="safe_guard_index_expert";//维权主页专家
	public final static String SAFE_GUARD_INDEX_LEAGUE="safe_guard_index_league";//维权主页联盟机构
	
	
	public final static String SAFE_GUARD_APPLY_STATE_FAIL="驳回";
	public final static String SAFE_GUARD_APPLY_STATE_WAIT="未处理";
	public final static String SAFE_GUARD_APPLY_STATE_SUCCESS="已处理";
	
	public final static String LEAGUE_FORBIDDEN_START="启用禁用";
	public final static String LEAGUE_FORBIDDEN_CLEAR="解锁禁用";
	
	public final static int PATENT_REMINDER_EXPIRE_DAY=20; //园区专利缴费提醒提前多少天发送提醒通知(邮件、短信)
	
	public final static int OTHER_MODULE = 0; //其他模块
	public final static int EXPERT_MODULE = 1; //专家模块
	public final static int POLICY_MODULE = 2; //政策卉块
	public final static int PROJECT_MODULE = 3;//项目模块
	public final static int ACHIEVEMENT_MODULE = 4;//成果模块
	
	public final static String MODULE_SAVE = "C"; //其他模块
	public final static String MODULE_UPDATE = "U"; //其他模块
	public final static String MODULE_DELETE = "D"; //其他模块
	public final static String MODULE_SELECT  = "R"; //其他模块
	
	
	
}
