package com.sungeon.bos.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sungeon.bos.job.QuartzJobManager;
import com.sungeon.bos.service.IBaseService;
import com.sungeon.bos.util.FileUtil;

@Component
public class InitializeListener implements ApplicationListener<ContextRefreshedEvent> {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private IBaseService baseService;
	@Autowired
	private QuartzJobManager quartzJobManager;
	@Value("${Param.InitSQL.Status}")
	private String isInitSQL;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent evt) {
		// TODO Auto-generated method stub
		// 防止重复执行。
		if (evt.getApplicationContext().getParent() == null) {
			// 启动定时任务
			quartzJobManager.initScheduleJobs();

			if (isInitSQL.equals("N"))
				return;
			log.info("******************接口程序初始化开始******************");
			// 初始化SQL
			baseService.initSQL(loadInitSQL());
			// 初始化存储过程、方法、触发器
			baseService.initProcedure(loadInitProcedure());
			// 初始化推送时间
			initThirdTime();
			log.info("******************接口程序初始化完成******************");
		}
	}

	private void initThirdTime() {
		Integer timeid = baseService.getThirdTimeId("StoragePropelTime");
		if (null == timeid) {
			timeid = baseService.initThirdTime("StoragePropelTime");
			if (null != timeid && timeid > 0)
				log.info("****初始化库存推送时间成功（仅在第一次部署时运行）****");
		}
		timeid = baseService.getThirdTimeId("StoreStoragePropelTime");
		if (null == timeid) {
			timeid = baseService.initThirdTime("StoreStoragePropelTime");
			if (null != timeid && timeid > 0)
				log.info("***初始化门店库存推送时间成功（仅在第一次部署时运行）***");
		}
		timeid = baseService.getThirdTimeId("ProductPropelTime");
		if (null == timeid) {
			timeid = baseService.initThirdTime("ProductPropelTime");
			if (null != timeid && timeid > 0)
				log.info("***初始化商品推送时间成功（仅在第一次部署时运行）***");
		}
	}

	private List<String> loadInitSQL() {
		List<String> sqls = new ArrayList<String>();
		String path = InitializeListener.class.getResource("/").getPath().substring(1) + "sqls/";
		List<File> files = FileUtil.listFiles(path);
		for (File file : files) {
			if (file.getName().endsWith(".sql")) {
				String content = FileUtil.readFileByChar(file, "gbk");
				String[] sqlarray = content.split("(;\\s*\\r\\n)|(;\\s*\\n)");
				sqls.addAll(Arrays.asList(sqlarray));
			}
		}
		return sqls;
	}

	private List<File> loadInitProcedure() {
		List<File> files = new ArrayList<File>();
		String path = InitializeListener.class.getResource("/").getPath().substring(1) + "sqls/";
		List<File> allFiles = FileUtil.listFiles(path);
		for (File file : allFiles) {
			if (file.isDirectory())
				continue;

			if (file.getName().endsWith(".prc"))
				files.add(file);
			else if (file.getName().endsWith(".fnc"))
				files.add(file);
			else if (file.getName().endsWith(".trg"))
				files.add(file);
			else
				continue;
		}
		return files;
	}

}
