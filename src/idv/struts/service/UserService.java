package idv.struts.service;

import java.util.List;

import idv.onlycheng.vo.User;
import idv.struts.exception.UserException;

public interface UserService {
	public List<User> list() throws UserException;

}
