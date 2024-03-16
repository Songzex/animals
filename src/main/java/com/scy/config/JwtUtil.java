package com.scy.config;//JwtUtil.java

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author Scott
 * @Date 2018-07-12 14:23
 * @Desc JWT工具类
 **/
@Component
public class JwtUtil {

	// Token过期时间30分钟（用户登录过期时间是此时间的两倍，以token在reids缓存时间为准）
	public static final long EXPIRE_TIME = 30 * 60 * 1000;

	/**
	 * 校验token是否正确
	 *
	 * @param token  密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String email, String secret) {
		try {
			// 根据密码生成JWT效验器
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("email", email).build();
			// 效验TOKEN
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 *
	 * @return token中包含的用户名
	 */
	public static String getUsername(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("username").asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 生成签名,5min后过期
	 *
	 * @param username 用户名
	 * @param secret   用户的密码
	 * @return 加密的token
	 */
	public static String cretaeToken(String username, String secret) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		// 附带username信息
		return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);

	}

	/**
	 * 根据request中的token获取用户账号
	 * 
	 * @param request
	 * @return
	 */
	public static String getUseruseridByToken(HttpServletRequest request){
		String accessToken = request.getHeader("X-Access-Token");
		String email = getUsername(accessToken);
		if (StringUtils.isEmpty(email)) {
			Throw EmailNULLeXCEPTIION  ;
		}
		return email;
	}
	
	/**
	  *  从session中获取变量
	 * @param key
	 * @return
	 */
//	public static String getSessionData(String key) {
//		//${myVar}%
//		//得到${} 后面的值
//		String moshi = "";
//		if(key.indexOf("}")!=-1){
//			 moshi = key.substring(key.indexOf("}")+1);
//		}
//		String returnValue = null;
//		if (key.contains("#{")) {
//			key = key.substring(2,key.indexOf("}"));
//		}
//		if (StringUtils.isNotEmpty(key)) {
//			HttpSession session = SpringContextUtils.getHttpServletRequest().getSession();
//			returnValue = (String) session.getAttribute(key);
//		}
//		//结果加上${} 后面的值
//		if(returnValue!=null){returnValue = returnValue + moshi;}
//		return returnValue;
//	}
//

//	public static void main(String[] args) throws Exception{
//		 /*String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjUzMzY1MTMsInVzZXJuYW1lIjoiYWRtaW4ifQ.xjhud_tWCNYBOg_aRlMgOdlZoWFFKB_givNElHNw3X0";
//		 System.out.println(JwtUtil.getUsername(token));*/
//
//		String token = JwtUtil.sign("admin", "123456");
//		System.out.println(token);
//	}
}

