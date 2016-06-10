package com.perficient.hr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.dao.NotificationDAO;

@Controller
@RequestMapping("/v-notification")
public class NotificationController {

	@Autowired
	private NotificationDAO notificationDAO;
	
	@RequestMapping(value="/loadNotificationCount",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public long loadNotificationCount(HttpServletRequest request){
		HttpSession session = request.getSession();
		return notificationDAO.getNotificationCount(session.getAttribute("userId").toString());
	}
	
}
