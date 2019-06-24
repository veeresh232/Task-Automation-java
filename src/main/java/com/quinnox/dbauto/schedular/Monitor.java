package com.quinnox.dbauto.schedular;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quinnox.dbauto.checker.Functionality;
import com.quinnox.dbauto.connection.CreateConnection;
import com.quinnox.dbauto.mail.MailSender;
import com.quinnox.dbauto.model.Query;
@Component
public class Monitor {
	
	@Autowired
	private MailSender mailSender;
	
	@Scheduled(cron = "0 * * ? * *")
	public void doMonitor() {
		System.out.println("Schedule started");
		Functionality functionality=new Functionality();
		List<Query> queries=functionality.checkQuery();
		System.out.println(queries.size());
		
			mailSender.sendMail(queries);
			
		
		
	}

}
