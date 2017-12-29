package cn.et.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro {

	public static void main(String[] args) {
		//从配置文件中读取用户的权限信息 
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//获取当前用户
		Subject currentUser = SecurityUtils.getSubject();
		//当前用户的会话
		Session session = currentUser.getSession();
		/**
		 * 用户包括两部分：
		 *   principals（本人）：表示用户的标识信息 比如用户名 用户地址等
		 *   credentials（凭证）：表示用户用于登录的凭证 比如密码 证书等
		 */
		//判断是否登录
		if (!currentUser.isAuthenticated()) {
			
			//用户名和密码
			UsernamePasswordToken token = new UsernamePasswordToken("wqm1", "123321");
			
			//登录
			try {
				currentUser.login(token);
				System.out.println("登录成功");
			} catch (UnknownAccountException uae) {
				System.out.println("账号错误");
			}catch (IncorrectCredentialsException ice) {
				System.out.println("密码不匹配");
			}catch (LockedAccountException lae) {
				System.out.println("账号被锁定");
			}catch (AuthenticationException ae) {
				System.out.println("位置异常");
			}
		}
	}
}
