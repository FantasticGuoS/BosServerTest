package com.sungeon.bos.junit;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springmvc-config.xml" })
public class JunitTestEmail {

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void sendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();// 消息构造器
		message.setFrom("420560822@qq.com");// 发件人
		message.setTo("516987289@qq.com");// 收件人
		message.setSubject("Spring Email Test");// 主题
		message.setText("hello world!!");// 正文
		mailSender.send(message);
		System.out.println("邮件发送完毕");
	}

	/**
	 * 发送带有附件的email
	 * 
	 * @throws MessagingException
	 */
	@Test
	public void sendEmailWithAttachment() throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);// 构造消息helper，第二个参数表明这个消息是multipart类型的
		helper.setFrom("testFrom@163.com");
		helper.setTo("testTo@qq.com");
		helper.setSubject("Spring Email Test");
		helper.setText("这是一个带有附件的消息"); // 使用Spring的FileSystemResource来加载fox.png
		FileSystemResource image = new FileSystemResource("D:\\fox.png");
		System.out.println(image.exists());
		helper.addAttachment("fox.png", image);// 添加附加，第一个参数为添加到Email中附件的名称，第二个人参数是图片资源
		mailSender.send(message);
		System.out.println("邮件发送完毕");
	}

	/**
	 * 发送富文本内容的Email
	 * 
	 * @throws MessagingException
	 */
	@Test
	public void sendRichEmail() throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("testFrom@163.com");
		helper.setTo("testTo@qq.com");
		helper.setSubject("Spring Email Test");
		helper.setText("<html><body><img src='cid:testLogo'><h4>Hello World!!!</h4></body></html>", true);// 第二个参数表明这是一个HTML
		// src='cid:testLogo'表明在消息中会有一部分是图片并以testLogo来进行标识
		ClassPathResource image = new ClassPathResource("logo.jpg");
		System.out.println(image.exists());
		helper.addInline("testLogo", image);// 添加内联图片，第一个参数表明内联图片的标识符，第二个参数是图片的资源引用
		mailSender.send(message);
	}

	@Test
	public void sendEmailByVelocity() throws MessagingException {
		VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.setProperty("resource.loader", "class");
		props.setProperty("class.resource.loader.class", ClassPathResource.class.getName());
		velocityEngine.setVelocityProperties(props);

		Map<String, Object> modal = new HashMap<String, Object>();
		modal.put("name", "薛小强");
		modal.put("text", "这是一个用Velocity生成的模板");
		// 使用VelocityEngineUtils将Velocity模板与模型数据合并成String
		String emailText = "<!DOCTYPE html><html><body><img src='cid:logo'><h4>Hello ${name}</h4><h3>${text}</h3></body></html>";// VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
																																	// "emailTemplate.vm",
																																	// "UTF-8",
																																	// modal);

		MimeMessage message = mailSender.createMimeMessage();
		// 第三个参数设置编码，否则如果有汉字会出现乱码问题
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom("testForm@163.com");
		helper.setTo("testTo@qq.com");
		helper.setSubject("Spring Email Test");
		helper.setText(emailText, true);
		ClassPathResource image = new ClassPathResource("logo.jpg");
		helper.addInline("logo", image);
		mailSender.send(message);
		System.out.println("邮件发送完毕");
	}

	public static void main(String[] args) throws MessagingException, MalformedURLException {
		// TODO Auto-generated method stub
		// JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		// mailSender.setHost("smtp.qq.com");// 指定用来发送Email的邮件服务器主机名
		// mailSender.setPort(25);// 默认端口，标准的SMTP端口
		// mailSender.setUsername("420560822@qq.com");// 用户名
		// mailSender.setPassword("kifcqqbmvauncadg");// 密码
		//
		// MimeMessage message = mailSender.createMimeMessage();
		// MimeMessageHelper helper = new MimeMessageHelper(message, true);
		// helper.setFrom("420560822@qq.com");// 发件人
		// helper.setTo("516987289@qq.com");// 收件人
		// helper.setSubject("Spring Email Test——RichEmail");// 主题
		// helper.setText(
		// "<!DOCTYPE html><html><body><img src='cid:testLogo'><a
		// href='http://www.baidu.com' target='_blank'>Baidu</a></body></html>",
		// true);// 正文
		// File file = new File("D:/Document/Image/logo.jpg");
		// helper.addInline("testLogo", file);
		// mailSender.send(message);
		// System.out.println("邮件发送完毕");

		sendEmail("420560822@qq.com", "516987289@qq.com", "恭喜注册成功");
	}

	/**
	 * 发送带附件的html格式邮件
	 */
	public static void sendEmail(String from, String to, String subject) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		MimeMessage msg = null;
		try {
			msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(getMailText(), true); // true表示text的内容为html

			// 添加内嵌文件，第1个参数为cid标识这个文件,第2个参数为资源
			helper.addInline("welcomePic", new File("D:/Document/Image/logo.jpg")); // 附件内容
		} catch (Exception e) {
			throw new RuntimeException("error happens", e);
		}
		mailSender.send(msg);
		System.out.println("邮件发送成功...");
	}

	/**
	 * 通过模板构造邮件内容，参数content将替换模板文件中的${content}标签。
	 */
	private static String getMailText() throws Exception {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.getConfiguration().setDirectoryForTemplateLoading(new File("D:/Document/ftl/"));
		// 通过指定模板名获取FreeMarker模板实例
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("registe.ftl");

		// FreeMarker通过Map传递动态数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "Test"); // 注意动态数据的key和模板标签中指定的属性相匹配
		map.put("password", "abc123");

		// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		return htmlText;
	}

}