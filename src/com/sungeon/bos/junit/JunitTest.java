package com.sungeon.bos.junit;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.dao.IVIPDao;
import com.sungeon.bos.dbcp.DataSourceTypeManager;
import com.sungeon.bos.entity.ThirdTime;
import com.sungeon.bos.service.IBaseService;
import com.sungeon.bos.service.IProductService;
import com.sungeon.bos.service.IWeChatService;
import com.sungeon.bos.service.sd.ISDBaseService;
import com.sungeon.bos.util.SystemProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springmvc-config.xml" })
public class JunitTest {

	@Autowired
	private IBaseService baseService;
	@Autowired
	private ISDBaseService sdBaseService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IWeChatService weChatService;
	// @Autowired
	// private IVIPService vipService;
	@Autowired
	private IVIPDao vipDao;

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void testBase() {
		System.out.println(baseService.getThirdTime("WDTStoreStoragePropelTime"));
		System.out.println(DataSourceTypeManager.get());
		System.out.println(sdBaseService.getThirdTime("WDTStoreStoragePropelTime"));
		System.out.println(DataSourceTypeManager.get());
		DataSourceTypeManager.reset(); // 不添加此条语句，就会一直在第二数据源，程序不会自动回第一数据源
		System.out.println(baseService.getThirdTime("ProductPropelTime"));
		System.out.println(DataSourceTypeManager.get());
	}

	@Test
	public void testProduct() {
		ThirdTime time = new ThirdTime();
		time.setType("SSTime");
		time.setTime("2019-06-01 12:00:00");
		time.setDescription("推送时间");
		System.out.println("ID前：" + time.getId());
		baseService.insertThirdTime(time);
		System.out.println("ID后：" + time.getId());
	}

	@Test
	public void testStorage() {
		Date now = new Date();
		JSONArray jsons = productService.getStoragePropel(0, SystemProperties.ParamPropelDataCount, now);
		System.out.println(jsons);
		System.out.println(productService.addStoragePropelLog(jsons));
	}

	@Test
	public void testWeChat() {
		weChatService.getWeChatMessagePropel();
	}

	@Test
	public void testProcedure() {
		System.out.println(baseService.testProcedure(10));
	}

	@Test
	public void testVIP() {
		System.out.println(vipDao.queryVIPIdByCardno("Z18772869817"));
	}

	@Test
	public void testMail() {
		SimpleMailMessage message = new SimpleMailMessage();// 消息构造器
		message.setFrom("lgs_itachi@163.com");// 发件人
		message.setTo("516987289@qq.com");// 收件人
		message.setSubject("Spring Email Test");// 主题
		message.setText("hello world!!");// 正文
		mailSender.send(message);
		System.out.println("邮件发送完毕");
	}

}