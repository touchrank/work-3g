package jp.co.ha.web.exception;

import jp.co.ha.common.exception.BaseAppException;
import jp.co.ha.common.exception.ErrorCode;

/**
 * 健康情報例外クラス<br>
 *
 */
public class HealthInfoException extends BaseAppException {

	/**
	 * コンストラクタ<br>
	 * @param errorCode
	 * @param detail
	 */
	public HealthInfoException(ErrorCode errorCode, String detail) {
		super(errorCode, detail);
	}
}
