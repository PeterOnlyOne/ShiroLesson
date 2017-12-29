package cn.et.conf;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.et.shiro.dao.UserMapper;
import cn.et.shiro.entity.UserInfo;
@Component
public class MyDbRealm extends AuthorizingRealm {

	@Autowired
	UserMapper um;
	/**
	 *  获取当前用户的授权数据
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户名
		String username = principals.getPrimaryPrincipal().toString();
		//获取角色
		Set<String> roleList = um.queryRoleByName(username);
		Set<String> permsList = um.queryPermsByName(username);
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		sai.setRoles(roleList);
		sai.setStringPermissions(permsList);
		return sai;
	}

	/**
	 * 认证
	 * 返回值null表示认证失败，不是null则认证通过
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取页面传入的用户名和密码
		UsernamePasswordToken upt = (UsernamePasswordToken)token;
		
		UserInfo queryUser = um.queryUser(upt.getUsername());
		
		if (queryUser != null && queryUser.getPassword().equals(new String(upt.getPassword()))) {
			SimpleAccount sa = new SimpleAccount(upt.getUsername(), upt.getPassword(),"MyDbRealm");
			return sa;
		}
		return null;
	}

}
