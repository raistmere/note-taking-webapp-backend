package com.raistmere.notetakingwebapp;

import com.raistmere.notetakingwebapp.dao.UserDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NoteTakingWebappApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(NoteTakingWebappApplication.class, args);

		UserDaoImpl userDao = (UserDaoImpl) context.getBean(UserDaoImpl.class);

		System.out.println(userDao.getAllUsers());
		System.out.println(userDao.getUserByUsername("bunny"));
	}

}
