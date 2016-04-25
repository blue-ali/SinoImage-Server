package cn.net.sinodata.cm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.net.sinodata.framework.log.SinoLogger;
/**
 * 
  * @ClassName: Util 
  * @Description: 工具类
  * @author 马南
  * @date 2013-4-10 上午11:39:37 
  *
 */
public class Util {
	
	private static final SinoLogger logger = SinoLogger.getLogger(Util.class);
	private static int tk_no = 0;
	
	/**
	 * 生成工单编号，时间+2位整数组成
	 * @return
	 * @author manan
	 */
	public static String generateTransactionId(int count){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String currentTime = df.format(new Date());// new Date()为获取当前系统时间
		String cycleNo = String.format("%04d", generateCycleNo(count));	//长度不足2位前面补0
		System.out.println(cycleNo);
		return currentTime + cycleNo;
	}

	/**
	 * 生成0-99循环整数
	 * @return
	 * @author manan
	 */
	private synchronized static int generateCycleNo(int count){
		tk_no ++;
		if ( tk_no > count)
			tk_no = 0;
		return tk_no;
	}
	
	public static String getStackTraceMsg(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
	
	public static boolean isObjEmpty(Object ojb){
		if(ojb != null){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isStrEmpty(String str){
		if(str!=null && !"".equals(str.trim())){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isListEmpty(List<?> list){
		if(list != null && list.size() > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isMapEmpty(Map<?,?> map){
		if(map != null && map.size() > 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * ��ȡָ���������
	 */
	public static StringBuilder getFullMsg(byte[] buffer, int len, InputStream in) {
		StringBuilder msg = new StringBuilder();
		int counter = 0;
		try {
			int i = in.read(buffer);
			while (i > -1) {
				if (counter > 10) {
					break;
				}
				String str = new String(buffer, 0, i);
				msg.append(str);
				if (msg.toString().getBytes().length != len) {
					buffer = new byte[len - i];
					i = in.read(buffer);
				} else {
					break;
				}
				counter++;
			}
		} catch (Exception e) {
		}
		return msg;
	}

	public static int getMsgPrefix(InputStream in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0)
			return -1;
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}

	/**
	 * 字符串填充方法
	 * 
	 * @param src
	 *            The source string
	 * @param length
	 *            Length of the specific string
	 * @param filling
	 *            Which fill forward or backward the string if the length of
	 *            original string is not enough
	 * @param direction
	 *            <i>F</i> -- forward ; <i>B</i> -- backward
	 * 
	 * @return theSpecificString
	 */
	public static String getSpecificString(String src, int length, char filling, String direction) {
		StringBuffer retStr = null;
		try {
			byte[] bytes = src.getBytes();
			int len = bytes.length;
			if (len > length) {
				byte rbyte[] = new byte[length];
				memcpy(rbyte, bytes);
				retStr = new StringBuffer(new String(rbyte));
			} else {
				retStr = new StringBuffer();
				if (direction.equals("F")) {
					for (int i = 0; i < (length - len); i++) {
						retStr.append(filling);
					}
					retStr.append(src);
				} else if (direction.equals("B")) {
					retStr.append(src);
					for (int i = 0; i < (length - len); i++) {
						retStr.append(filling);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return retStr.toString();
	}

	public static void memcpy(byte[] dest, byte[] src) {
		int len = dest.length < src.length ? dest.length : src.length;
		System.arraycopy(src, 0, dest, 0, len);
	}

	public static byte[] memmerge(byte[] src, byte[] merge) {
		int slen = src.length;
		int mlen = merge.length;
		byte[] r = new byte[slen + mlen];
		System.arraycopy(src, 0, r, 0, slen);
		System.arraycopy(merge, 0, r, slen, mlen);
		return r;
	}

	public static byte[] package_Str(byte[] message) throws IOException {
		int len = message.length;
		byte[] msg = new byte[len + 4];
		for (int SQ = 0; SQ < 4; SQ++) {
			msg[SQ] = (byte) (len >>> (24 - SQ * 8));
		}
		System.arraycopy(message, 0, msg, 4, message.length);
		return msg;
	}

	public static byte[] readBytes(InputStream in) throws IOException {
		byte[] read = new byte[0];
		byte[] buffer = new byte[1024];
		int len = 0;
		int count = 0;
		while ((len = in.read(buffer)) != -1) {
			byte[] btemp = new byte[len];
			memcpy(btemp, buffer);
			read = memmerge(read, btemp);
			count += len;
		}
		System.out.println(count + " Bytes Have Read.");
		return read;
	}

	public static byte[] readBytes(int length, InputStream in) throws IOException {
		byte[] read = new byte[0];
		byte[] buffer = new byte[length];
		int counter = 0;
		int i = in.read(buffer);
		while (i > -1) {
			if (counter > 10) {
				System.out
						.println(" 10 times failed trying to read " + length + " bytes data , please check the network");
				read = null;
				break;
			}
			if (i != length) {
				byte[] temp = new byte[i];
				memcpy(temp, buffer);
				memmerge(read, temp);
				buffer = new byte[length - i];
				i = in.read(buffer);
			} else {
				memmerge(read, buffer);
				break;
			}
			counter++;
		}
		return read;
	}
	
	/**
	 * 设置流程回退时的流程变量
	 * @param varsMap
	 * 			流程变量map
	 * @param nodeName
	 * 			当前流程所在节点名
	 * @param excptionCode
	 * 			回退时异常码
	 * Administrator
	 */
	public static void setExceptionBackInfo(Map<String, Object> varsMap, String nodeName, String excptionCode){
//		varsMap.put("CURRENT_NODE", nodeName);// 当前节点名
//		varsMap.put("EXCEPTION_FLAG", "true");// 回退
//		varsMap.put("EXCEPTION_CODE", excptionCode);// 异常码
		varsMap.put("ENTRY_CHECK_FLAG", "false");// 不去复核
		varsMap.put("EXCEPTION_FLAG", "true");// 异常码
		varsMap.put("ENTRY_ACCOUNT_FLAG", "false");// 异常码
		varsMap.put("ENTRY_AUTH_FLAG", "false");// 异常码
		varsMap.put("COMMON_ZX_FLAG", "false");// 异常码
		varsMap.put("COMMON_ZW_FLAG", "false");// 异常码
		varsMap.put("COMMON_QT_FLAG", "true");// 异常码
		varsMap.put("QTCL_QTSQ_FLAG", "false");// 异常码
		varsMap.put("QTCL_YY_FLAG", "false");// 异常码
		varsMap.put("QTCL_RETURN_FLAG", "false");// 异常码
	}
	
	/**
	 * 读取文件内容到字符串
	 * @param filePath
	 * @return
	 * chenwentao
	 * @throws IOException 
	 */
	public static String file2Str(String filePath) throws IOException {
		File file = new File(filePath);
		return file2Str(file);
	}
	
	/**
	 * 读取文件内容到字符串
	 * @param filePath
	 * @return
	 * chenwentao
	 * @throws IOException 
	 */
	public static String file2Str(File file) {
		StringBuffer buf = new StringBuffer( );
		if(file==null || !file.exists()) {
			logger.error("文件" + file.getAbsoluteFile() + "不存在");
			return null;
		}
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String str = null;
			while((str = in.readLine()) != null) {
				buf.append(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buf.toString();
	}
	
	/**
	 *  整型转换字节数组
	 * @param n
	 * @return
	 * chenwentao
	 */
	public static byte[] intToBytes(int n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}
	
	/**
	 *  字节数组到整型
	 * @param b
	 * @return
	 * chenwentao
	 */
	public static int bytesToInt(byte b[]) {
		return (b[3] & 0xff) | ((b[2] & 0xff) << 8) | ((b[1] & 0xff) << 16) | ((b[0] & 0xff) << 24);
	}
}
