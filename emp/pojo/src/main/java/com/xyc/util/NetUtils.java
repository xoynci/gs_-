package com.xyc.util;

public class NetUtils {

	/**
	 * 列如：255.255.255.0 转换成 24
	 * 
	 * @param mask 255.255.255.255 格式
	 * @return 1~32
	 */
	public static Integer maskToWei(String mask) {
		StringBuffer sbf;
		String str;
		int inetmask = 0, count = 0; // 子网掩码缩写代码
		String[] ipList = mask.split("\\.");
		for (int n = 0; n < ipList.length; n++) {

			sbf = toBin(Integer.parseInt(ipList[n]));
			str = sbf.reverse().toString();
			//System.out.println(ipList[n] + "---" + str);

			// 统计2进制字符串中1的个数
			count = 0;
			for (int i = 0; i < str.length(); i++) {
				i = str.indexOf('1', i); // 查找 字符'1'出现的位置
				if (i == -1) {
					break;
				}
				count++; // 统计字符出现次数
			}
			inetmask += count;

		}
		return inetmask;

		// "子网掩码缩写代码："+inetmask;
	}

	public static StringBuffer toBin(int x) {
		StringBuffer result = new StringBuffer();
		result.append(x % 2);
		x /= 2;
		while (x > 0) {
			result.append(x % 2);
			x /= 2;
		}
		return result;
	}

}
