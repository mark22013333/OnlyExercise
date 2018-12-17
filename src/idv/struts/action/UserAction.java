package idv.struts.action;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.opensymphony.xwork2.Action;

import idv.onlycheng.vo.User;
import idv.struts.exception.UserException;
import idv.struts.service.UserService;
import idv.struts.service.impl.UserServiceImpl;

@WebServlet("/usersys")
public class UserAction {
	private UserService userService = new UserServiceImpl();
	private List<User> list = null;

	/**
	 * @return list
	 */
	public List<User> getList() {
		return list;
	}

	/**
	 * @param list
	 *                 set list
	 */
	public void setList(List<User> list) {
		this.list = list;
	}

	public String list() throws UserException {
		list = userService.list();
		return Action.SUCCESS;
	}

}
