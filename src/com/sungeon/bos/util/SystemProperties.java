package com.sungeon.bos.util;

import java.io.IOException;
import java.io.InputStream;

public class SystemProperties {
	private static PropertiesUtil pros = null;
	public static final Long origDate = 1262275200000L;
	public static final Long defaultLong = Long.parseLong("0");

	public static String AppId;
	public static String AppSecret;
	public static String Version;
	public static String Brand;
	public static String ScheduleGroup;
	public static String ParamStore;
	public static String ParamSignStatus;
	public static String ParamSignType;
	public static String ParamSignQhsStatus;
	public static int ParamDataCount;
	public static String ParamVIPMobileRepeatable;
	public static String ParamEOrderAutoSubmit;
	public static int ParamTimeCalled;
	public static String ParamPropelStatus;
	public static String ParamPropelURL;
	public static int ParamPropelDataCount;
	public static String ParamProductDIMBrand;
	public static String ParamProductDIMClass;
	public static String MethodBase;
	public static String MethodProductGet;
	public static String MethodStorageGet;
	public static String MethodVIPGet;
	public static String MethodVIPAdd;
	public static String MethodVIPModify;
	public static String MethodVIPUnique;
	public static String MethodVIPUpgrade;
	public static String MethodVIPIntegralGet;
	public static String MethodVIPIntegralAdjust;
	public static String MethodTicketGet;
	public static String MethodTicketAdd;
	public static String MethodTicketVerify;
	public static String MethodEOrderAdd;
	public static String MethodEOrderReturnAdd;
	public static String MethodEOrderStatusApplyReturn;
	public static String MethodEOrderStatusReturn;
	public static String MethodRetailGet;
	public static String MethodRetailAdd;
	public static String MethodCustomerAdd;

	static {
		InputStream inputStream = SystemProperties.class.getClassLoader()
				.getResourceAsStream("system.properties");
		pros = new PropertiesUtil();
		try {
			pros.load(inputStream);
			AppId = pros.getString("AppId", "");
			AppSecret = pros.getString("AppSecret", "");
			Version = pros.getString("Version", "2.0");
			Brand = pros.getString("Brand", "");
			ScheduleGroup = pros.getString("ScheduleGroup", "");
			
			ParamStore = pros.getString("Param.Store", "");
			ParamSignStatus = pros.getString("Param.Sign.Status", "Y");
			ParamSignType = pros.getString("Param.Sign.Type", "T3");
			ParamSignQhsStatus = pros.getString("Param.Sign.qhs.Status", "N");
			ParamDataCount = pros.getInt("Param.Data.Count", 300);
			ParamVIPMobileRepeatable = pros.getString(
					"Param.VIP.Mobile.Repeatable", "N");
			ParamEOrderAutoSubmit = pros.getString("Param.EOrder.AutoSubmit",
					"N");
			ParamTimeCalled = pros.getInt("Param.Time.Called", 0);
			ParamPropelStatus = pros.getString("Param.Propel.Status", "N");
			ParamPropelURL = pros.getString("Param.Propel.URL", "");
			ParamPropelDataCount = pros.getInt("Param.Propel.Data.Count", 300);
			ParamProductDIMBrand = pros.getString("Param.Product.DIM.Brand",
					"M_DIM1_ID");
			ParamProductDIMClass = pros.getString("Param.Product.DIM.Class",
					"M_DIM2_ID");

			MethodBase = pros.getString("Method.Base", "Burgeon.Bos.base");

			MethodProductGet = pros.getString("Method.Product.get",
					"Burgeon.Bos.product.get");
			MethodStorageGet = pros.getString("Method.Storage.get",
					"Burgeon.Bos.storage.get");

			MethodVIPGet = pros.getString("Method.VIP.get",
					"Burgeon.Bos.vip.get");
			MethodVIPAdd = pros.getString("Method.VIP.add",
					"Burgeon.Bos.vip.add");
			MethodVIPModify = pros.getString("Method.VIP.modify",
					"Burgeon.Bos.vip.modify");
			MethodVIPUnique = pros.getString("Method.VIP.unique",
					"Burgeon.Bos.vip.unique");
			MethodVIPUpgrade = pros.getString("Method.VIP.upgrade",
					"Burgeon.Bos.vip.upgrade");
			MethodVIPIntegralGet = pros.getString("Method.VIP.integral.get",
					"Burgeon.Bos.vip.integral.get");
			MethodVIPIntegralAdjust = pros.getString(
					"Method.VIP.integral.adjust",
					"Burgeon.Bos.vip.integral.adjust");

			MethodTicketGet = pros.getString("Method.Ticket.get",
					"Burgeon.Bos.ticket.get");
			MethodTicketAdd = pros.getString("Method.Ticket.add",
					"Burgeon.Bos.ticket.add");
			MethodTicketVerify = pros.getString("Method.Ticket.verify",
					"Burgeon.Bos.ticket.verify");

			MethodEOrderAdd = pros.getString("Method.EOrder.add",
					"Burgeon.Bos.EOrder.add");
			MethodEOrderReturnAdd = pros.getString("Method.EOrder.return.add",
					"Burgeon.Bos.EOrder.return.add");
			MethodEOrderStatusApplyReturn = pros.getString(
					"Method.EOrder.status.applyreturn",
					"Burgeon.Bos.EOrder.status.applyreturn");
			MethodEOrderStatusReturn = pros.getString(
					"Method.EOrder.status.return",
					"Burgeon.Bos.EOrder.status.return");

			MethodRetailGet = pros.getString("Method.Retail.get",
					"Burgeon.Bos.retail.get");
			MethodRetailAdd = pros.getString("Method.Retail.add",
					"Burgeon.Bos.retail.add");

			MethodCustomerAdd = pros.getString("Method.Customer.add",
					"Burgeon.Bos.customer.add");
		} catch (IOException ex) {
			System.out.println("file is not exist");
		}
	}

}
