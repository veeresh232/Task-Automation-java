package com.quinnox.dbauto.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.quinnox.dbauto.model.Query;

@Component("mailSender")
public class MailSender {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;

	@Async
	public void sendMail(List<Query> queries) {
		
		MimeMessage message=mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper mail= new MimeMessageHelper(message,true);
			mail.setSubject("webMethods Database Query running for a long time");
			//mail.setFrom("qtvt@quinnox.com");
			mail.setTo("veereshm@quinnox.com");
			mail.setCc("rkumarp@coca-cola.com");
			String text=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "top.vm", new HashMap());
			for(Query query:queries) {
			Map model= new HashMap();
			model.put("sid",query.getSid());
			model.put("serial", query.getSerial());
			int minutes=(int) (query.getTime()/60);
			model.put("time",minutes);
			model.put("sqlText", query.getSqlText());
			 text=text+VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "queryMailTemplate.vm","UTF-8", model);
			}
			text+=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "bottom.vm", new HashMap());
			System.out.println(text);
			mail.setText(text, true);
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sending mail");
		mailSender.send(message);
		System.out.println("Done!!!");
		
	}

}
