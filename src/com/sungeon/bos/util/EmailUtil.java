package com.sungeon.bos.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

@Component
public class EmailUtil {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * 发送普通文本邮件
	 * 
	 * @param from
	 *            发送人邮箱
	 * @param to
	 *            接收人邮箱
	 * @param subject
	 *            邮件标题
	 * @param text
	 *            邮件正文
	 * @return
	 */
	public boolean sendSimpleEmail(String from, String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage(); // 消息构造器
			message.setFrom(from); // 发件人
			message.setTo(to); // 收件人
			message.setSubject(subject); // 主题
			message.setText(text); // 正文
			mailSender.send(message);
			return true;
		} catch (MailException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 发送带附件邮件
	 * 
	 * @param from
	 *            发送人邮箱
	 * @param to
	 *            接收人邮箱
	 * @param subject
	 *            邮件标题
	 * @param text
	 *            邮件正文
	 * @param files
	 *            附件文件
	 * @return
	 */
	public boolean sendEmailWithAttachment(String from, String to, String subject, String text, List<File> files) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			// 构造消息helper，第二个参数表明这个消息是multipart类型的
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			// 使用Spring的FileSystemResource来加载File
			for (File file : files) {
				FileSystemResource fsr = new FileSystemResource(file);
				// 添加附加，第一个参数为添加到Email中附件的名称，第二个人参数是File文件
				helper.addAttachment(file.getName(), fsr);
			}
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} catch (MailException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 发送带附件邮件
	 * 
	 * @param from
	 *            发送人邮箱
	 * @param to
	 *            接收人邮箱
	 * @param subject
	 *            邮件标题
	 * @param htmlText
	 *            邮件正文——HTML富文本：如：
	 * 
	 *            <pre>
	 * {@code<html><body><img src='cid:testLogo'><a href=
	'cid:testURL'>Baidu</a></body></html>}
	 *            </pre>
	 * 
	 * @param mappers
	 *            附件文件：如：<br>
	 *            key=testLogo，value=D:/testLogo.png；<br>
	 *            key=testURL，value=http://www.baidu.com
	 * @return
	 */
	public boolean sendRichEmail(String from, String to, String subject, String htmlText, Map<String, String> mappers) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			// 构造消息helper，第二个参数表明这个消息是multipart类型的
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlText, true); // 第二个参数表明这是一个HTML
			// <html><body><img
			// src='cid:testLogo'><h4>HelloWorld!!!</h4></body></html>
			// src='cid:testLogo'表明在消息中会有一部分是图片并以testLogo来进行标识
			for (String key : mappers.keySet()) {
				String value = mappers.get(key);
				ClassPathResource cpr = new ClassPathResource(value);
				// 添加内联图片，第一个参数表明内联图片的标识符，第二个参数是图片的资源引用
				helper.addInline(key, cpr);
			}
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		} catch (MailException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return false;
		}
	}

	private static final String ENCODING = "utf-8";

	/**
	 * 发送带附件的html格式邮件
	 */
	public void sendEmail(String from, String to, String subject) {
		MimeMessage msg = null;
		try {
			msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
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
	private String getMailText() throws Exception {
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
