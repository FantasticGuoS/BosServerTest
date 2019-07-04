package com.sungeon.bos.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Jaxb2工具类
 * 
 * @author GuoS
 * @create 2013-3-29 下午2:40:14
 */
public class JaxbUtil {

	/**
	 * JavaBean转换成xml 默认编码UTF-8
	 * 
	 * @param obj
	 * @param writer
	 * @return
	 */
	public static String convertToXml(Object obj) {
		return convertToXml(obj, "UTF-8");
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// 是否省略xml头声明信息
			// marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToBean(String xml, Class<T> clazz) {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}