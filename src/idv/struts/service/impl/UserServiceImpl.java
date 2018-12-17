package idv.struts.service.impl;

import java.util.List;

import idv.onlycheng.vo.User;
import idv.struts.dao.UserDao;
import idv.struts.dao.UserDaoImpl;
import idv.struts.exception.UserException;
import idv.struts.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	public List<User> list() throws UserException {
		return userDao.list();
	}

}
