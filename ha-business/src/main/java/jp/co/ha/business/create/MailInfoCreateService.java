package jp.co.ha.business.create;

import jp.co.ha.common.entity.MailInfo;

/**
 * メール情報作成サービスインターフェース<br>
 *
 */
public interface MailInfoCreateService {

	/**
	 * メール情報を作成する<br>
	 * @param mailInfo
	 */
	void create(MailInfo mailInfo);



}