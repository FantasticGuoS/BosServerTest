package com.sungeon.bos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

import com.alibaba.fastjson.JSONObject;

public class RsaUtils {

	/**
	 * 生成公钥和私钥
	 * 
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		map.put("public", publicKey);
		map.put("private", privateKey);
		return map;
	}

	/**
	 * 使用模和指数生成RSA公钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA
	 * /None/NoPadding】
	 * 
	 * @param modulus
	 *            模
	 * @param exponent
	 *            指数
	 * @return
	 */
	public static RSAPublicKey getPublicKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用模和指数生成RSA私钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA
	 * /None/NoPadding】
	 * 
	 * @param modulus
	 *            模
	 * @param exponent
	 *            指数
	 * @return
	 */
	public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		// 模长
		int key_len = publicKey.getModulus().bitLength() / 8;
		// 加密数据长度 <= 模长-11
		String iosDatas = new String(data.getBytes("GBK"), "ISO-8859-1");
		String[] datas = splitString(iosDatas, key_len - 11);
		String mi = "";
		// 如果明文长度大于模长-11则要分组加密
		for (String s : datas) {
			mi += byte2HexStr(cipher.doFinal(s.getBytes("ISO-8859-1")));
		}
		// for (String s : datas) {
		// mi += bcd2Str(cipher.doFinal(s.getBytes("ISO-8859-1")));
		// }
		return mi;
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		// 模长
		int key_len = privateKey.getModulus().bitLength() / 8;
		System.out.println(data);
		byte[] bytes = data.getBytes("UTF-8");
		for (byte b : bytes)
			System.out.print(b);
		System.out.println();
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
		for (byte b : bcd)
			System.out.print(b);
		System.out.println();
		// 如果密文长度大于模长则要分组解密
		String ming = "";
		byte[][] arrays = splitArray(bcd, key_len);
		for (byte[] arr : arrays) {
			ming += new String(cipher.doFinal(arr), "ISO-8859-1");
		}
		return new String(ming.getBytes("ISO-8859-1"));
	}

	/**
	 * ASCII码转BCD码
	 * 
	 */
	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	public static byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	/**
	 * BCD转字符串
	 */
	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	/**
	 * 拆分字符串
	 */
	public static String[] splitString(String string, int len) {
		int x = string.length() / len;
		int y = string.length() % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		String[] strings = new String[x + z];
		String str = "";
		for (int i = 0; i < x + z; i++) {
			if (i == x + z - 1 && y != 0) {
				str = string.substring(i * len, i * len + y);
			} else {
				str = string.substring(i * len, i * len + len);
			}
			strings[i] = str;
		}
		return strings;
	}

	/**
	 * 拆分数组
	 */
	public static byte[][] splitArray(byte[] data, int len) {
		int x = data.length / len;
		int y = data.length % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		byte[][] arrays = new byte[x + z][];
		byte[] arr;
		for (int i = 0; i < x + z; i++) {
			arr = new byte[len];
			if (i == x + z - 1 && y != 0) {
				System.arraycopy(data, i * len, arr, 0, y);
			} else {
				System.arraycopy(data, i * len, arr, 0, len);
			}
			arrays[i] = arr;
		}
		return arrays;
	}

	public static RSAPrivateKey loadPrivateKey(InputStream in) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			return loadPrivateKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Util.decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (IOException e) {
			throw new Exception("私钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	public static RSAPublicKey loadPublicKey(InputStream in) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			return loadPublicKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Util.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (IOException e) {
			throw new Exception("公钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/*
	 * MD5加密
	 */
	public static String MD5MsgDigestDemo(String pubDate) {
		MessageDigest md5Digest = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 更新要计算的内容
		md5Digest.update(pubDate.getBytes());
		// 完成哈希计算，得到摘要
		byte[] md5Encoded = md5Digest.digest();
		System.out.println("原文: " + pubDate);
		System.out.println("MD5摘要: " + byte2HexStr(md5Encoded));
		return byte2HexStr(md5Encoded);
	}

	/*
	 * 实现字节数组向十六进制的转换方法一
	 */
	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/*
	 * 测试方法
	 */
	public static void main(String[] args) throws Exception {

		// HashMap<String, Object> map = RsaUtils.getKeys();
		// //生成公钥和私钥
		// RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
		// RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
		//
		// //模
		// String modulus = publicKey.getModulus().toString();
		// //公钥指数
		// String public_exponent = publicKey.getPublicExponent().toString();
		// //私钥指数
		// String private_exponent = privateKey.getPrivateExponent().toString();
		// 明文
		JSONObject obj = new JSONObject();
		obj.put("logNo", "201804270298407869");
		obj.put("UserId", "");
		obj.put("BalDate", "20180427");
		obj.put("AgentId", "172");
		obj.put("TxnLogId", "9115248173556540156342038");
		obj.put("BusinessId", "800461000001090");
		obj.put("SDTermNo", "95126798");
		obj.put("TxnCode", "N001");
		obj.put("PayChannel", "2");
		obj.put("TxnDate", "20180427");
		obj.put("TxnTime", "162238");
		obj.put("TxnAmt", "43.9");
		obj.put("TxnStatus", "1");
		obj.put("BankType", "");
		obj.put("OfficeId", "91524817356294700943");
		obj.put("ChannelId", "9115248173556540156342038");
		obj.put("attach", "");
		obj.put("CrdFlg", "");

		// String dataText = obj.toString();
		//
		// System.out.println("原数据：" + dataText);
		// // 使用模和指数生成公钥和私钥
		// // RSAPublicKey pubKey = RsaUtils.getPublicKey(modulus,
		// // public_exponent);
		// // RSAPrivateKey priKey = RsaUtils.getPrivateKey(modulus,
		// // private_exponent);
		// /*
		// * 读取公钥
		// */
		// File filepubkey = new File("D:/Document/pkcs8_rsa_private_key.pem");
		// FileInputStream finpubkey = new FileInputStream(filepubkey);
		// RSAPublicKey pubKey = loadPublicKey(finpubkey);
		// /*
		// * 公钥加密
		// */
		// String pubDate = encryptByPublicKey(dataText, pubKey);
		//
		// System.out.println("加密数据：" + pubDate);
		//
		// // 对加密数据进行MD5加密 *记得拼接MD5密钥*
		// String MD5key = "e10adc3949ba59abbe56e057f20f883e";
		// String MD5Data = pubDate + MD5key;// 加签数据
		// System.out.println("MD5加签原数据：" + MD5Data);
		//
		// String signValue = MD5MsgDigestDemo(MD5Data);
		// System.out.println("MD5加签后数据：" + signValue);
		//
		// // 组请求数据 测试。。
		// // JSONObject object = new JSONObject();
		// // object.put("Datas", pubDate);
		// // object.put("singValue", md5MsgDigestData);
		// //
		// // System.out.println(object.toString());
		/*
		 * 读取私钥
		 */
		File file = new File("D:/Document/pkcs8_rsa_private_key.pem");
		FileInputStream fin = new FileInputStream(file);
		RSAPrivateKey priKey = loadPrivateKey(fin);

		/*
		 * 私钥解密
		 */
		String pubDate = "C5D4938E31FCB8CCF825C5ECBC052923E5C866AC69C7C8669A976C4DADF12FCBB932BEDDF4962D820F86743981F7D90B859CCAE41949AAA49188E9BD13EFA5C26A53C43433D213177E193C4AA0BA7B3F22088E6559A5227D57AEB529115357C12254134F2F39557B05ABAD244D8C9573BE625E6776597037B96BADF31255724B7C6D869E6333DFF1D4BAC1D6C05707A80680F21DFEF002CA531F3D578E4ADE2A307B3F5CA20827DBF8C078B78F5F081764053E2FDD0AF3B321C1B5BEA4AC2CDC146B0085286A82DB1E662E9F72362F8FBE651B9C007F72C0EE9EF09F5A785B56C315EDED9C6BC0A197790662336FB4A211837769F2809087BDB9ABAB406D22E7A481CCC70B26A5F9629064DE59290A2BFDFA6AEED5305B94EE46D32C20B6F3C867B1467E6EDB275D9BBABFEED1ACA7CF78315B4E308E8E08EC1A6AEBFA29BCC3FB7954559CBDCC29B16D9FB1313C54DE5CA5A30DFE0B2DE8DD890F86531C9546DBBFC1C5902F58B3CA22ACAEF8BED4493F0C2CB296AD635BA3F4E52E46A0B13D00788C397208780A3E543197B5EB8F392E7E8FC7EE37B75BBC54C69D0B6CFA8369B9F1429CB2DA3CC498119B41ECE7078AC418C4FD983C4D62D4C73A37D624D1DC204873994D2D7473826371A874B871258291279C027725907AD8A69342CE13CFBD15A4F96D2BE9CC42A941976D065CA9B0B9725AB99985639C083809A7072A";
		String priDate = decryptByPrivateKey(pubDate, priKey);

		System.out.println("解密数据：" + priDate);

		// long time = System.currentTimeMillis();
		// System.out.println(System.currentTimeMillis()-time);
	}

}