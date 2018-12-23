package com.doctortech.fhq.utils;

public class MsgTemplateUtil {
	
	public static final int SMS_FAIL_NUM_LIMIT = 3;
	
	public static final int EMAIL_FAIL_NUM_LIMIT = 3;
	
	public static final String SMS_CONTENT_PARAM_SEPARATOR = "|_|";

	/**
	 *  用户报名
	 *  发送对象：东莞交流会报名者
	 * 	内容：【%s】，恭喜您成功报名参加东莞高层次人才交流洽谈会，我们将根据企业匹配情况在11月初发出正式邀请函。
	 *  短信模板：118835
	 */
	public static String Msg_Template_User_Apply_HD_DongGuan="User_Apply_HD_DongGuan";
	
	
	/**
	 *  需求结束投标提醒
	 *  发送对象：发布人(雇主)
	 * 	内容：尊敬的用户【%s】，您发布的需求【%s】已经结束投标了，请再7天内完成选稿操作哦！%s
	 *  短信模板：
	 */
	public static String Msg_Template_Requirement_Deadline_Tips="Requirement_Deadline_Tips";
	
	/**
	 *  需求公示提醒（悬赏金模式）
	 *  发送对象：获奖人
	 * 	内容：尊敬的用户【%s】，恭喜您在投标的需求【%s】上获得%s！%s
	 *  短信模板：
	 */
	public static String Msg_Template_Requirement_Publicity_Tips1="Requirement_Publicity_Tips1";
	/**
	 *  需求公示提醒（普通模式）
	 *  发送对象：中标人
	 * 	内容：尊敬的用户【%s】，恭喜您中标了需求【%s】！%s
	 *  短信模板：
	 */
	public static String Msg_Template_Requirement_Publicity_Tips2="Requirement_Publicity_Tips2";
	
	
	
	/**
	 * 雇主发布需求
	 */
	/**
	 *  
	 *  接收对象：雇主
	 *  标题：需求未通过审核
	 * 	内容：尊敬的【用户名称】：很遗憾，您发布的需求【需求标题】 未通过审核，
	 * 未通过审核的原因：【管理员选择或填写的原因】，感谢您对孵化圈的信任。
	 * 如有特殊情况，请小窗联系客服（做超链接，弹出IM,并打开和客服的对话），我们将协助您解决问题。
	 *  
	 */
	public static String Msg_Template_Requirement_Publish_Tips1="Requirement_Publish_Tips1";
	
	/**
	 *  
	 *  接收对象：雇主
	 *  标题：需求通过审核
	 * 	内容：尊敬的【用户名称】：恭喜您，您发布的需求【需求标题】 通过审核，感谢您对孵化圈的信任。
	 * 如果您在使用孵化圈过程中有任何问题，请小窗联系客服（做超链接，弹出IM,并打开和客服的对话），我们将协助您解决问题。
	 *  
	 */
	public static String Msg_Template_Requirement_Publish_Tips2="Requirement_Publish_Tips2";
	
	/**
	 *  
	 *  接收对象：雇主
	 *  标题：需求发布成功
	 * 	内容：尊敬的【用户名称】：恭喜您，您发布的需求【需求标题】成功发布，感谢您对孵化圈的信任。
	 * 如果您在使用孵化圈过程中有任何问题，请小窗联系客服（做超链接，弹出IM,并打开和客服的对话），我们将协助您解决问题。
	 *  
	 */
	public static String Msg_Template_Requirement_Publish_Tips3="Requirement_Publish_Tips3";
	
	/**
	 * 服务商投标
	 */
	
	/**
	 *  
	 *  接收对象：雇主
	 *  标题：服务商【xxx】投稿了您发布的需求
	 * 	内容：尊敬的【用户名称】：【服务商】投稿了您发布的需求【需求标题】 ，您可以前往查看Ta的投稿并作选稿处理，立即前往查看《。
	 *  
	 */
	public static String Msg_Template_Requirement_Tender_Tips1="Requirement_Tender_Tips1";
	
	/**
	 * 雇主选稿
	 */
	/**
	 *  
	 *  接收对象：服务商
	 *  标题：雇主【xxx】查看了您的稿件
	 * 	内容：尊敬的【用户名称】：【雇主】于【2017年2月5日】查看了您的投稿。
	 */
	public static String Msg_Template_Requirement_Choose_Tips1="Requirement_Choose_Tips1";
	/**
	 *  
	 *  接收对象：服务商
	 *  标题：您的稿件已中标（专业招标）
	 * 	内容：尊敬的【用户名称】：恭喜您，您的稿件已中标，平台将指派工作人员帮助您开展线下协作工作。如有疑问，可小窗联系客服（做超链接，弹出IM,并打开和客服的对话）
	 */
	public static String Msg_Template_Requirement_Choose_Tips2="Requirement_Choose_Tips2";
	
	/**
	 *  
	 *  接收对象：服务商
	 *  标题：您的稿件获得二等奖（诚意金悬赏）
	 * 	内容：尊敬的【用户名称】：您的稿件于【2017年2月5日】获得【二等奖】，奖金已发放至您的现金账户，查看我的现金账户。
	 * 
	 */
	public static String Msg_Template_Requirement_Choose_Tips3="Requirement_Choose_Tips3";
	/**
	 *  
	 *  接收对象：雇主
	 *  标题：只剩1天截止选稿啦，请您及时选稿！
	 * 	内容：尊敬的【用户名称】：离截止选稿只剩1天的时间了，请您尽快对所有提交的稿件做出评奖或淘汰处理哦！如有疑问，可小窗联系客服（做超链接，弹出IM,并打开和客服的对话）
	 * 
	 */
	public static String Msg_Template_Requirement_Choose_Tips4="Requirement_Choose_Tips4";
	
	/**
	 *  
	 *  接收对象：服务商
	 *  标题：您的稿件被淘汰
	 * 	内容：尊敬的【用户名称】：很遗憾，您的稿件未被采纳，请您再接再厉哦！您也可以查看一下其他类似需求进行投稿。立即查看
	 * 
	 */
	public static String Msg_Template_Requirement_Choose_Tips5="Requirement_Choose_Tips5";
	
	/**
	 * 评价
	 */
	/**
	 *  
	 *  接收对象：服务商
	 *  标题：雇主【xxx】评价了您
	 * 	内容：尊敬的【用户名称】：雇主于【2017年2月5日】对您的服务做出了【好评】，立即前往查看
	 * 
	 */
	public static String Msg_Template_Requirement_Evaluate_Tips1="Requirement_Evaluate_Tips1";
	
	/**
	 *  
	 *  接收对象：雇主
	 *  标题：服务商【xxx】评价了您
	 * 	内容：尊敬的【用户名称】：服务商于【2017年2月5日】对您做出了【好评】，立即前往查看
	 * 
	 */
	public static String Msg_Template_Requirement_Evaluate_Tips2="Requirement_Evaluate_Tips2";
	
	/**
	 * 充值
	 */
	/**
	 *  
	 *  接收对象：充值用户
	 *  标题：您已成功充值【100】元
	 * 	内容：尊敬的【用户名称】：您于【2017年2月5日】成功充值【100元】，查看我的现金账户
	 * 
	 */
	public static String Msg_Template_Requirement_Recharge_Tips1="Requirement_Recharge_Tips1";
	
	/**
	 * 提现
	 */
	/**
	 *  
	 *  接收对象：提现用户
	 *  标题：您的提现申请提交成功
	 * 	内容：尊敬的【用户名称】：您于【2017年2月5日】成功申请提现，平台将在3个工作日内将提现金额发放至您绑定的支付宝账户，请注意查收。
	 * 
	 */
	public static String Msg_Template_Requirement_Cash_Tips1="Requirement_Cash_Tips1";
	
	/**
	 * 用户注册
	 */
	/**
	 *  
	 *  接收对象：所有注册用户
	 *  标题：注册成功
	 * 	内容：尊敬的【用户名称】：感谢您对孵化圈的信任，当您收到这条信息的时候，您已经成功注册为孵化圈会员。
	 * 在这里，您可以感受到诚信、活泼的交易氛围，体验到高效、真诚的交易服务，我们竭力为解决企业的科技创新而不断努力。
	 * 如有任何疑问，欢迎随时与我们联系，我们将竭诚为您服务！联系客服（做超链接，弹出IM,并打开和客服的对话）
	 * 
	 */
	public static String Msg_Template_Requirement_Register_Tips1="Requirement_Register_Tips1";
	
	/**
	 * 用户认证
	 */
	/**
	 *  
	 *  接收对象：进行实名认证的用户
	 *  标题：实名认证成功
	 * 
	 */
	public static String Msg_Template_Requirement_Verified_Tips1="Requirement_Verified_Tips1";
	/**
	 *  
	 *  接收对象：进行企业认证的用户
	 *  标题：企业认证成功
	 * 
	 */
	public static String Msg_Template_Requirement_Verified_Tips2="Requirement_Verified_Tips2";
	/**
	 *  
	 *  接收对象：进行手机认证的用户
	 *  标题：手机认证成功
	 * 
	 */
	public static String Msg_Template_Requirement_Verified_Tips3="Requirement_Verified_Tips3";
	/**
	 *  
	 *  接收对象：进行科技伯乐认证的用户
	 *  标题：科技伯乐认证成功
	 * 
	 */
	public static String Msg_Template_Requirement_Verified_Tips4="Requirement_Verified_Tips4";
	
	/**
	 * 活动报名
	 */
	/**
	 *  
	 *  接收对象：报名活动的用户
	 *  标题：【活动名称】报名成功
	 *  内容：尊敬的【用户名称】：您已成功报名【活动名称】，活动将于【2017月1月6日】在【地点】举办，
	 *  请您准时参加，若举办方因其他原因修改了本次活动的时间地点我们会及时通知您！
	 * 
	 */
	public static String Msg_Template_Requirement_Activity_Tips1="Requirement_Activity_Tips1";
	
	/**
	 * 【博士科技孵化圈】恭喜您成功报名首届湖南科技成果转化大赛暨第三届“中科创赛”湖南区域赛，了解活动进展可向组委会刘恋18819454443（微信同号）咨询。退订回N。
	 */
	public static String Msg_Template_Golden_Hunan_Activity_Tips1="Golden_Hunan_Activity_Tips1";
	
	/**
	 * 恭喜您完成第六届金博奖预报名，请前往孵化圈大赛页面提交参赛资料完成报名，大赛链接已发到您的预留邮箱。感谢支持。【广东博士创新发展促进会】
	 */
	public static String Msg_Template_Golden_Wx_Activity_Tips1="Golden_Wx_Activity_Tips1";
	/**
	 * 恭喜您成功报名第六届“金博奖”，您的报名资料已收到，更多大赛资讯请留意主委会微信或电话通知，感谢您的支持。【广东博士创新发展促进会】
	 */
	public static String Msg_Template_Golden_Six_Activity_Tips1="Golden_Six_Activity_Tips1";
	
	
	/**
	 * 系统推荐需求
	 */
	/**
	 *  
	 *  接收对象：填了ifu卡片的用户 
	 *  标题：为您推荐了任务【任务名称】
	 *  内容：尊敬的【用户名称】：系统根据数据分析，为您推荐了新的任务【需求名称】，赶紧去投稿赚钱吧。立即查看
	 * 
	 */
	public static String Msg_Template_Requirement_Card_Tips1="Requirement_Card_Tips1";
	
	/**
	 * 运营自定义（主要包括一些促销活动、优惠活动等。格式同上述一致，消息标题+消息详情）
	 */
	/**
	 *  
	 *  接收对象：用户 
	 *  标题：xxx
	 *  内容：尊敬的【用户名称】：系统根据数据分析，为您推荐了新的任务【需求名称】，赶紧去投稿赚钱吧。立即查看
	 * 
	 */
	public static String Msg_Template_Requirement_Operate_Tips1="Requirement_Operate_Tips1";
	
	
	
	/**
     *  
     *  接收对象：雇主
     *  标题：系统自动您匹配的一些专家，成果，团队
     *  内容：尊敬的【用户名称】：我们系统为您匹配内容如下，【标题1,标题2，标题3】，请到会员中心查看，感谢您对孵化圈的信任。
     *  
     */
    public static String Msg_Template_Requirement_Want_Tips1="Requirement_Want_Tips1";
	
	
	
    /**
     * 东莞活动 - 人才报名成功
	 * 您好，您已成功提交2017东莞高层次人才交流洽谈会报名材料，大会组委会将根据企业单位的对接意向，于11月下旬向高校人才发出正式邀请函，请您静候通知。
	 */
	public static String Msg_Template_DongGuan_Activity_Talent_Tips="DongGuan_Activity_Talent_Tips";
	/**
     * 东莞活动 - 高校报名成功
	 */
	public static String Msg_Template_DongGuan_Activity_College_Tips="DongGuan_Activity_College_Tips";
}