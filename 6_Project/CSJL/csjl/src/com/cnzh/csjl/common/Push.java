package com.cnzh.csjl.common;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

public class Push {
	private static String APP_SECRET_KEY="rD14j70+FNwpA23qpD5CiA==";
	private static String PACKAGENAME="com.asus.aaa";
	public static void tibeizhiMessage() throws Exception {
	    Constants.useOfficial();
	    Sender sender = new Sender(APP_SECRET_KEY);
	    String messagePayload = "孩子踢被子啦，快去看看！";
	    String title = "踢被子提醒";
	    String description = "孩子踢被子啦，快去看看！";
	    String alias = "1";
	    Message message = new Message.Builder()
	        .title(title)
	        .description(description).payload(messagePayload)
	        .restrictedPackageName("com.asus.aaa")
	        .notifyType(-1)     // 使用默认提示音提示
	        .build();
	    Result result = sender.sendToAlias(message, alias, 3);
	    System.out.println("Server response: "+"MessageId: " + result.getMessageId()
	            + " ErrorCode: " + result.getErrorCode().toString()
	            + " Reason: " + result.getReason());
	}
	
	public static void lachouchouMessage() throws Exception {
	    Constants.useOfficial();
	    Sender sender = new Sender(APP_SECRET_KEY);
	    String messagePayload = "孩子最近几分钟可能会拉臭臭哟！";
	    String title = "拉臭臭提醒";
	    String description = "孩子最近几分钟可能会拉臭臭哟！";
	    String alias = "1";
	    Message message = new Message.Builder()
	        .title(title)
	        .description(description).payload(messagePayload)
	        .restrictedPackageName("com.asus.aaa")
	        .notifyType(1)     // 使用默认提示音提示
	        .build();
	    Result result = sender.sendToAlias(message, alias, 3);
	    System.out.println("Server response: "+"MessageId: " + result.getMessageId()
        + " ErrorCode: " + result.getErrorCode().toString()
        + " Reason: " + result.getReason());
	}
	
	public static void wenduMessage() throws Exception {
	    Constants.useOfficial();
	    Sender sender = new Sender(APP_SECRET_KEY);
	    String messagePayload = "孩子体温出现异常，请尽快处理！！";
	    String title = "体温异常警报";
	    String description = "孩子体温出现异常，请尽快处理！！";
	    String alias = "1";
	    Message message = new Message.Builder()
	        .title(title)
	        .description(description).payload(messagePayload)
	        .restrictedPackageName("com.asus.aaa")
	        .notifyType(1)     // 使用默认提示音提示
	        .extra(Constants.EXTRA_PARAM_SOUND_URI, "android.resource://" + PACKAGENAME + "/raw/jingbao")
	        .build();
	    Result result = sender.sendToAlias(message, alias, 3);
	    System.out.println("Server response: "+"MessageId: " + result.getMessageId()
        + " ErrorCode: " + result.getErrorCode().toString()
        + " Reason: " + result.getReason());
	}
}
