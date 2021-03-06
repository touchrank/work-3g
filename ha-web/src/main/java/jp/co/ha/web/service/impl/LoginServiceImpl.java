package jp.co.ha.web.service.impl;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ha.business.find.AccountSearchService;
import jp.co.ha.common.entity.Account;
import jp.co.ha.common.util.StringUtil;
import jp.co.ha.web.form.LoginForm;
import jp.co.ha.web.service.LoginService;

/**
 * ログインサービス実装クラス
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	/** アカウント検索サービス */
	@Autowired
	private AccountSearchService accountSearchService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean invalidPassword(LoginForm loginForm) {

		String inputPassword = loginForm.getPassword();
		Account account = accountSearchService.findByUserId(loginForm.getUserId());
		String userPassword = account.getPassword();
		return !inputPassword.equals(userPassword);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registSession(HttpSession session, String userId) {
		session.setAttribute("userId", userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existAccount(LoginForm loginForm) {

		Account account = accountSearchService.findByUserId(loginForm.getUserId());
		return Objects.nonNull(account.getUserId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean invalidAccount(LoginForm loginForm) {

		Account account = accountSearchService.findByUserId(loginForm.getUserId());
		return StringUtil.isTrue(account.getDeleteFlag());
	}

}
