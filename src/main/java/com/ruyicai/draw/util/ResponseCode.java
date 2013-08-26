package com.ruyicai.draw.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * 响应码.
 * @author ryc
 */
public enum ResponseCode {

	OK("0", "成功"), 
	ERROR("500", "服务器错误"),
	PARAMTER_ERROR("501", "参数错误"),
	MAC_ERROR("502", "Mac生成错误"),
	PARAMTER_NAME_ERROR("503", "用户名不匹配"),
	
	UserReg_PassMD5Error("070006", "加密密码错误"), 
	UserReg_UserMacError("070007", "生成用户校验码错误"),
	UserReg_UserExists("0013", "用户已注册"),
	UserReg_UserPause("000012", "用户已暂停"),
	UserReg_AccountMacError("070008", "账户校验码错误"),
	UserReg_MobileIdError("0019", "手机号错误"),
	UserReg_EmailError("0020", "邮箱错误"),
	UserReg_NicknameExists("100105", "昵称已存在"),
	
	UserMod_UserNoEmpty("100001", "用户userno为空"),
	UserMod_UserNotExists("100002", "用户不存在"),
	UserMod_MobileidBind("100003", "用户手机号已绑定"),
	UserMod_EmailBind("100004", "用户邮箱已绑定"),
	UserMod_ZfbuserifBind("100006", "用户支付宝user_id已绑定"),
	UserMod_NicknameNotExists("100205", "昵称不存在"),
	UserMod_StateNotNormal("100206", "用户状态不正常"),
	UserMod_UsernameNotallowMod("100207", "用户名不允许修改"),
	
	Draw_Expired("00001", "抽奖结束");
	
	public String value;
	
	public String memo;
	
	ResponseCode(String value, String memo) {
		
		this.value = value;
		this.memo = memo;
	}
	
	private static final Map<String, ResponseCode> lookup = new HashMap<String, ResponseCode>();

	static {
		for (ResponseCode s : EnumSet.allOf(ResponseCode.class)) {
			lookup.put(s.value, s);
		}
	}

	public static ResponseCode get(String value) {
		return lookup.get(value);
	}
}
