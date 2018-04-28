package jp.co.ha.business.find.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ha.business.find.MailInfoSearchService;
import jp.co.ha.common.dao.MailInfoDao;
import jp.co.ha.common.entity.MailInfo;

/**
 * メール情報検索サービス実装クラス<br>
 *
 */
@Service
public class MailInfoSearchServiceImpl implements MailInfoSearchService {

	/** メール情報Dao */
	@Autowired
	private MailInfoDao mailInfoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MailInfo findMailInfoByUserId(String userId) {
		return this.mailInfoDao.getMailInfoByUserId(userId);
	}
}