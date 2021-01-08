package com.xyc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 肖运才
  *   功能方法适合对网络配置文件、巡检记录文件、字符串进行按正则表达式进行截取。
 */
public class MyStringUtils {

	/**
	 * @param filepath
	 * @param startTag
	 * @param endTag
	 * @return 文件里以startTag开始endTag结束的所有字符串列表，Tag是正则表达式
	 */
	public static List<String> subStringByFile(String filepath, String startTag, String endTag) {
		List<String> outList = new ArrayList<String>();
		startTag = startTag.replaceAll("\n", "$");
		endTag = endTag.replaceAll("\n", "$");
		/* 读取数据 */
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filepath)), "UTF-8"));
			String line = null;
			String addStr = "";
			boolean start = false;
			int i=0;
			while ((line = br.readLine()) != null) {
				if (line.matches(startTag)) {
					start = true;
				}
				if (start) {
					i++;
					if(i==1) {
						addStr = addStr + line + "\n";
						continue;
					}else if(i>1) {
						addStr = addStr + line + "\n";
					}
				}				
				if (start == true & line.matches(endTag)) {
					start = false;
					outList.add(addStr);
					addStr = "";
					i=0;
					continue;
				}
			}
			br.close();
		} catch (Exception e) {
			System.err.println("read errors :" + e);
		}
		return outList;

	}

	/**
	 * @param filepath
	 * @param startTag
	 * @param endTag
	 * @return 字符串以"\n"分割，然后返回startTag开始endTag结束的所有字符串列表，Tag是正则表达式
	 */
	public static List<String> subStringByString(String string, String startTag, String endTag) {
		List<String> outList = new ArrayList<String>();
		startTag = startTag.replaceAll("\n", "$");
		endTag = endTag.replaceAll("\n", "$");
		String[] stringarr = string.split("\n");
		String addStr = "";
		boolean start = false;
		int i=0;
		for (String line : stringarr) {
			if (line.matches(startTag)) {
				start = true;
			}
			if (start) {
				i++;
				if(i==1) {
					addStr = addStr + line + "\n";
					continue;
				}else if(i>1) {
					addStr = addStr + line + "\n";
				}
			}				
			if (start == true & line.matches(endTag)) {
				start = false;
				outList.add(addStr);
				addStr = "";
				i=0;
				continue;
			}
		}
		return outList;
	}
	
	/**
	 * @param lineStr
	 * @param regex
	 * @return 返回lineStr中 regex规则第一个（）匹配的内容。
	 */
	public static String subStringByLine(String lineStr,String regex) {
		String outstr=null;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineStr);
		if(matcher.find()) {
			outstr=matcher.group(1);
		}
		return outstr;
	}
	/**
	 * @param lineStr
	 * @param regex
	 * @return 返回lines 中 regex规则第一个（）匹配的内容。
	 */
	public static List<String> subStringByLines(String[] lines,String regex) {
		List<String> outlist=new ArrayList<String>();
		for (String line : lines) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			if(matcher.find()) {
				outlist.add(matcher.group(1));
			}
		}
		return outlist;
	}
	
	/**
	 * 类似linux下的grep命令 regex规则第一个（）匹配的内容。
	 * @return
	 */
	public static List<String> grepByList(String regex,List<String> lines) {
		List<String> outlist=new ArrayList<String>();
		for (String line : lines) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			if(matcher.find()) {
				outlist.add(matcher.group(1));
			}
		}
		return outlist;
	}
	
	/**
	 * 类似linux下的grep命令 regex规则第一个（）匹配的内容。
	 * @return
	 */
	public static List<String> grepByFile(String regex,String filename) {
		List<String> outlist=new ArrayList<String>();
		/* 读取数据 */
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filename)), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(line);
				if(matcher.find()) {
					outlist.add(matcher.group(1));
				}
			}
			br.close();
		} catch (Exception e) {
			System.err.println("read errors :" + e);
		}
		return outlist;
	}
	/**
	 * @param list 需要过滤的list
	 * @param regex 正则条件
	 * @param included true返回包含条件的内容，false返回排除条件的内容
	 * @return 返回过滤后的结果
	 */
	public static List<String> filterByList(List<String> list,String regex,boolean included) {
			List<String> includedlist=new ArrayList<String>();
			List<String> excludedlist=new ArrayList<String>();
			for (String line : list) {
				if(line.matches(regex)) {
					includedlist.add(line);
				}else {
					excludedlist.add(line);
				}
			}
			if(included) {
				return includedlist;
			}
			return excludedlist;
	}
	
	public static List<String> array2list(String[] array) {
		return Arrays.asList(array);
	}
	
	public static String[] list2array(List<String> list) {
		return (String[]) list.toArray();
	}

}
