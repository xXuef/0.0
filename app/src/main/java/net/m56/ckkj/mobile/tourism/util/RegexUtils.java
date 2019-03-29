package net.m56.ckkj.mobile.tourism.util;

import junit.framework.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类 提供验证邮箱、手机号、电话号码、身份证号码、数字等方法
 */
public final class RegexUtils {

	public static boolean isphotos(String path) {

		String regex = "(?i).+?\\.(jpg|gif|bmp)";
		Pattern pattern = Pattern.compile(regex);

		Matcher match = pattern.matcher(path);
		boolean b = match.matches();
		return b;
	}

	public static boolean isMoney(String num) {
		String regex = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(num);
		boolean b = match.matches();
		return b;
	}

	/**
	 * 截取小数点后二位
	 */

	public static boolean subdianortwo(String num) {
		String trgex = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$";
		if (num.lastIndexOf(".") + 3 != num.length()) {
			return false;
		}

		return Pattern.matches(trgex, num);
	}

	/**
	 * 小数点只能一位
	 */

	public static boolean checkNumOrdian(String num) {
		String trgex = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$";
		return Pattern.matches(trgex, num);
	}

	/**
	 * 验证Email
	 *
	 * @param email
	 *
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkEmail(String email) {
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		return Pattern.matches(regex, email);
	}

	/**
	 * 验证中文英文数字
	 */
	public static boolean checklogin(String email) {
		String regex = "[a-zA-Z0-9\u4e00-\u9fa5]+";
		return Pattern.matches(regex, email);
	}
	public static boolean checkloginUser(String email) {
		String regex = "[a-zA-Z0-9-]+";
		return Pattern.matches(regex, email);
	}
	/**
	 * 验证英文数字特殊符号
	 */
	public static boolean checkloginOrEnglichMaths(String email) {
		String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,20}$";
		return Pattern.matches(regStr, email);
	}

	/**
	 * 验证身份证号码
	 *
	 * @param idCard
	 *            居民身份证号码15位或18位，最后一位可能是数字或字母
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkIdCard(String idCard) {
		String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
		return Pattern.matches(regex, idCard);
	}

	/**
	 * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 *
	 * @param mobile
	 *            移动、联通、电信运营商的号码段
	 *            <p>
	 *            移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 *            、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
	 *            </p>
	 *            <p>
	 *            联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
	 *            </p>
	 *            <p>
	 *            电信的号段：133、153、180（未启用）、189
	 *            </p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkMobile(String mobile) {
		String regex = "(\\+\\d+)?1[23456789]\\d{9}$";
		return Pattern.matches(regex, mobile);
	}

	/**
	 * 验证固定电话号码
	 *
	 * @param phone
	 *            电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
	 *            <p>
	 *            <b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9
	 *            的一位或多位数字， 数字之后是空格分隔的国家（地区）代码。
	 *            </p>
	 *            <p>
	 *            <b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
	 *            对不使用地区或城市代码的国家（地区），则省略该组件。
	 *            </p>
	 *            <p>
	 *            <b>电话号码：</b>这包含从 0 到 9 的一个或多个数字
	 *            </p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkPhone(String phone) {
		String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
		return Pattern.matches(regex, phone);
	}

	/**
	 * 验证整数（正整数和负整数）
	 *
	 * @param digit
	 *            一位或多位0-9之间的整数
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDigit(String digit) {
		String regex = "\\-?[1-9]\\d+";
		return Pattern.matches(regex, digit);
	}

	/**
	 * 验证整数和浮点数（正负整数和正负浮点数）
	 *
	 * @param decimals
	 *            一位或多位0-9之间的浮点数，如：1.23，233.30
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDecimals(String decimals) {
		String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
		return Pattern.matches(regex, decimals);
	}

	public static boolean checkDecimal_f(String decimals) {
		String regex = "^\\d{0,10}";
		return Pattern.matches(regex, decimals);
	}


	/**
	 * 验证空白字符
	 *
	 * @param blankSpace
	 *            空白字符，包括：空格、\t、\n、\r、\f、\x0B
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkBlankSpace(String blankSpace) {
		String regex = "\\s+";
		return Pattern.matches(regex, blankSpace);
	}

	/**
	 * 验证中文
	 *
	 * @param chinese
	 *            中文字符
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkChinese(String chinese) {
		String regex = "^[\u4E00-\u9FA5]+$";
		return Pattern.matches(regex, chinese);
	}

	/**
	 * 验证日期（年月日）
	 *
	 * @param birthday
	 *            日期，格式：1992-09-03，或1992.09.03
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkBirthday(String birthday) {
		String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
		return Pattern.matches(regex, birthday);
	}
	public static boolean mathA_Zd(String birthday) {
		String regex = "^[0-9a-zA_Z.]+$";
		return Pattern.matches(regex, birthday);
	}

	/**
	 * 验证URL地址
	 *
	 * @param url
	 *            格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或
	 *            http://www.csdn.net:80
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkURL(String url) {
		String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
		return Pattern.matches(regex, url);
	}

	/**
	 * 匹配中国邮政编码
	 *
	 * @param postcode
	 *            邮政编码
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkPostcode(String postcode) {
		String regex = "[1-9]\\d{5}";
		return Pattern.matches(regex, postcode);
	}

	/**
	 * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
	 *
	 * @param ipAddress
	 *            IPv4标准地址
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkIpAddress(String ipAddress) {
		String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
		return Pattern.matches(regex, ipAddress);
	}

	/**
	 * 正则表达式工具类测试
	 */

	/**
	 * 验证邮箱
	 */
	public void testCheckEmail() {
		boolean result = RegexUtils.checkEmail("zha2_ngsan@sina.com.cn");
		Assert.assertTrue(result);
	}

	/**
	 * 验证身份证号码
	 */
	public void testCheckIdCard() {
		boolean result = RegexUtils.checkIdCard("432403193902273273");
		Assert.assertTrue(result);
	}

	/**
	 * 验证手机号码
	 */
	public void testCheckMobile() {
		boolean result = RegexUtils.checkMobile("+8613620285733");
		Assert.assertTrue(result);
	}

	/**
	 * 验证电话号码
	 */
	public void testCheckPhone() {
		boolean result = RegexUtils.checkPhone("+860738-4630706");
		Assert.assertTrue(result);
	}

	/**
	 * 验证整数（正整数和负整数）
	 */
	public void testCheckDigit() {
		boolean result = RegexUtils.checkDigit("123132");
		Assert.assertTrue(result);
	}

	/**
	 * 验证小数和整数（正负整数和正负小数）
	 */
	public void testCheckDecimals() {
		boolean result = RegexUtils.checkDecimals("-33.2");
		Assert.assertTrue(result);
	}

	/**
	 * 验证空白字符
	 */
	public void testCheckBlankSpace() {
		boolean result = RegexUtils.checkBlankSpace("           ");
		Assert.assertTrue(result);
	}

	/**
	 * 匹配中文
	 */
	public void testCheckChinese() {
		boolean result = RegexUtils.checkChinese("中文");
		Assert.assertTrue(result);
	}

	/**
	 * 验证日期
	 */
	public void testCheckBirthday() {
		boolean result = RegexUtils.checkBirthday("1992/09/03");
		Assert.assertTrue(result);
	}

	/**
	 * 验证中国邮政编码
	 */
	public void testCheckPostcode() {
		boolean result = RegexUtils.checkPostcode("417100");
		Assert.assertTrue(result);
	}

	/**
	 * 验证URL地址
	 */
	public void testCheckURL() {
		boolean result = RegexUtils
				.checkURL("http://blog.csdn.com:80/xyang81/article/details?name=&abc=中文");
		Assert.assertTrue(result);
	}

	/**
	 * 验证IP地址
	 */
	public void testCheckIpAddress() {
		boolean result = RegexUtils.checkIpAddress("192.1.22.255");
		Assert.assertTrue(result);
	}

	public static boolean isNull(Integer categoryId) {

		if (categoryId == null) {
			return true;
		}
		return false;
	}

	public static boolean checkDate(String trim) throws ParseException {
		// date.after(new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_FULL);
		long millionSeconds = sdf.parse(trim).getTime();// 毫秒
		long millionSeconds_new = sdf.parse(
				DateUtils.getNow(DateUtils.FORMAT_FULL)).getTime();

		if (millionSeconds > millionSeconds_new) {
			return false;
		}

		return true;
	}
	public static boolean isNotBlankAndEmpty(String str) {
		if (str != null && !str.equals("null") && str.length() > 0) {
			return true;
		}
		return false;
	}
}