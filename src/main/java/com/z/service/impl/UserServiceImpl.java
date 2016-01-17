package com.z.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.z.model.User;
import com.z.service.UserService;
import com.z.utils.Page;
import com.z.utils.PageUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
	
    @Override
    public String getEntityName() {
        return "com.huajtech.model.User";
    }

	@Override
	public boolean checkUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		List<User> list = getList(map, null);
		return list == null || list.isEmpty();
	}

	@Override
	public boolean checkUsernameAndPassword(String username, String password) {
		return getUserByUsernameAndPassword(username, password) != null;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}
		//TODO password need 加密
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		Page page = PageUtil.getPage(0, 1);
		List<User> list = getList(map, page);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
