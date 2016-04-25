package cn.net.sinodata.cm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.net.sinodata.framework.util.AssertUtil;

public class BatchNoUtil {
	private static int cycleNo = 0;

	public static String generateBatchNo(String bankNo, String tellerNo, String iclsId) {
		AssertUtil.hasText(bankNo, "行号为空。");
		AssertUtil.isTrue(bankNo.length() <= 8, "行号长度异常，行号长度不能大于8位。【bankNo=" + bankNo + "】");
		AssertUtil.hasText(tellerNo, "柜员号为空。");
		AssertUtil.isTrue(tellerNo.length() <= 8, "柜员号长度异常，柜员号长度不能大于8位。【tellerNo=" + tellerNo + "】");
		AssertUtil.hasText(iclsId, "影像类型为空。");
		AssertUtil.isTrue(iclsId.length() <= 6, "影像类型长度异常，长度不能大于6位。【iclsId=" + iclsId + "】");

		StringBuffer sb = new StringBuffer();
		String bN = padRight(bankNo, 8, '0');
		String tN = padRight(tellerNo, 8, '0');
		String iD = padRight(iclsId, 6, '0');
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		String cN = String.format("%02d", new Object[] { Integer.valueOf(generateCycleNo()) });

		sb.append(bN);
		sb.append(tN);
		sb.append(iD);
		sb.append(dateStr);
		sb.append(cN);
		return sb.toString();
	}

	private static synchronized int generateCycleNo() {
		cycleNo += 1;
		if (cycleNo > 99) {
			cycleNo = 0;
		}
		return cycleNo;
	}

	public static String padRight(String srcStr, int destLen, char alexin) {
		AssertUtil.hasText(String.valueOf(alexin), "补位符为空");
		StringBuffer destStr = new StringBuffer();
		int srcLen = srcStr.length();
		if (srcLen >= destLen) {
			return srcStr;
		}
		int l = destLen - srcLen;
		destStr.append(srcStr);
		for (int i = 0; i < l; i++) {
			destStr.append(alexin);
		}
		return destStr.toString();
	}

	public static String getIclsID(String batchNo) {
		String iclsID00 = batchNo.substring(16, 22);
		String iclsID = trim0(iclsID00);
		return iclsID;
	}

	public static String trim0(String str) {
		return str.endsWith("0") ? trim0(str.substring(0, str.length() - 1)) : str;
	}

	public static String getBankNo(String batchNo) {
		String bankNo = batchNo.substring(0, 8);

		bankNo = bankNo.substring(0, 4);
		return bankNo;
	}

	public static String getTorgIdNo(String batchNo) {
		int lastE = batchNo.lastIndexOf("E") + 1;
		String orgNo = batchNo.substring(lastE, batchNo.length());
		return orgNo;
	}

	public static void main(String[] args) {
		String batchNo = generateBatchNo("1270", "212", "2325");
		System.out.println(batchNo);
		String bankNo = getBankNo(batchNo);
		System.out.println(bankNo);
		String iclsId = getIclsID(batchNo);
		System.out.println(iclsId);
		System.out.println(getTorgIdNo("0000000Exssxs00E1100010111460"));
		System.out.println(getTorgIdNo("00000000xssxs0021100010111460"));
	}
}
