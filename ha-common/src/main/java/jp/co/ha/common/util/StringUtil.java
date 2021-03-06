package jp.co.ha.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 文字列のUtilクラス<br>
 *
 */
public class StringUtil {

	public static final String COMMA = ",";
	public static final String HYPHEN = "-";
	public static final String COLON = ":";
	public static final String PERIOD = ".";
	public static final String EMPTY = "";
	public static final String EQUAL = "=";
	public static final String NEW_LINE = "\r\n";
	public static final String SPACE = " ";
	public static final String THRASH = "/";
	public static final String TRUE_FLAG = "1";
	public static final String FALSE_FLAG = "0";

	/** 半角数字 */
	public static final String HALF_NUMBER = "^[0-9]*$";
	/** 半角数字とピリオド */
	public static final String HALF_NUMBER_PERIOD = "^[0-9.]*$";

	/** 半角英数字 */
	public static final String HALF_CHAR = "^[0-9a-zA-Z]*$";

	private StringUtil() {
	}

	/**
	 * 区切りたい文字列を区切り文字で、区切ったリストを返す
	 * @param target
	 * @param delim
	 * @return result
	 */
	public static List<String> toStrList(String target, String delim) {

		if (Objects.isNull(target) || EMPTY.equals(target)) {
			return null;
		}
		List<String> result = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(target, COMMA);

		while (tokenizer.hasMoreTokens()) {
			result.add(tokenizer.nextToken().trim());
		}
		return result;

	}

	/**
	 * 空文字かどうか判定する<br>
	 * 空文字の場合true, それ以外の場合false<br>
	 * @param target
	 * @return 判定結果
	 */
	public static boolean isEmpty(String target) {
		return Objects.isNull(target) || EMPTY.equals(target.trim());
	}

	/**
	 * 指定されたflagがtrueかどうか判定する<br>
	 * @param flag
	 * @return
	 */
	public static boolean isTrue(String flag) {
		return TRUE_FLAG.equals(flag);
//		return CodeManager.getInstance().isEquals(MainKey.FLAG, SubKey.TRUE, flag);
	}

	/**
	 * 指定されたflagがfalseかどうか判定する<br>
	 * @see StringUtil#isTrue(String)
	 * @param flag
	 * @return
	 */
	public static boolean isFalse(String flag) {
		return !isTrue(flag);
	}

}
