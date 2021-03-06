package jp.co.ha.business.create.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ha.business.create.AccountCreateService;
import jp.co.ha.common.dao.AccountDao;
import jp.co.ha.common.entity.Account;

/**
 * アカウント情報作成サービスインターフェース実装クラス<br>
 *
 */
@Service
public class AccountCreateServiceImpl implements AccountCreateService {

	/** アカウント情報Dao */
	@Autowired
	private AccountDao accountDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Account account) {
		accountDao.create(account);
	}

}
