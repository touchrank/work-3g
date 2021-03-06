package jp.co.ha.business.create.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ha.business.create.MailInfoCreateService;
import jp.co.ha.common.dao.MailInfoDao;
import jp.co.ha.common.entity.MailInfo;

/**
 * メール情報作成サービスインターフェース実装クラス<br>
 *
 */
@Service
public class MailInfoCreateServiceImpl implements MailInfoCreateService {

	/** メール情報Dao */
	@Autowired
	private MailInfoDao mailInfoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(MailInfo mailInfo) {
		mailInfoDao.create(mailInfo);
	}

}
