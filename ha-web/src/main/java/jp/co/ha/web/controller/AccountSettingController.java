package jp.co.ha.web.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.ha.business.create.MailInfoCreateService;
import jp.co.ha.business.find.AccountSearchService;
import jp.co.ha.business.find.MailInfoSearchService;
import jp.co.ha.business.update.AccountUpdateService;
import jp.co.ha.common.entity.Account;
import jp.co.ha.common.entity.MailInfo;
import jp.co.ha.common.exception.AccountSettingException;
import jp.co.ha.common.system.SessionManageService;
import jp.co.ha.common.web.BaseWizardController;
import jp.co.ha.web.form.AccountSettingForm;
import jp.co.ha.web.service.AccountSettingService;
import jp.co.ha.web.validator.AccountSettingValidator;
import jp.co.ha.web.view.ManageWebView;

/**
 * 健康管理_アカウント設定コントローラ<br>
 *
 */
@Controller
public class AccountSettingController implements BaseWizardController<AccountSettingForm, AccountSettingException> {

	/** アカウント設定サービス */
	@Autowired
	private AccountSettingService accountSettingService;

	/** アカウント検索サービス */
	@Autowired
	private AccountSearchService accountSearchService;
	/** アカウント更新サービス */
	@Autowired
	private AccountUpdateService accountUpdateService;
	/** メール情報検索サービス */
	@Autowired
	private MailInfoSearchService mailInfoSearchService;
	/** メール情報作成サービス */
	@Autowired
	private MailInfoCreateService mailInfoCreateService;
	/** session管理サービス */
	@Autowired
	private SessionManageService sessionService;

	/**
	 * Validateを設定<br>
	 * @param binder
	 */
	@Override
	@InitBinder(value = "AccountSettingForm")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new AccountSettingValidator());
	}

	/**
	 * Formを返す<br>
	 * @return
	 */
	@ModelAttribute
	public AccountSettingForm setUpForm(HttpServletRequest request) {

		// セッションからユーザIDを取得
		String userId = (String) sessionService.getValue(request, "userId");

		// アカウント情報を検索
		Account account = accountSearchService.findByUserId(userId);
		// メール情報を検索
		MailInfo mailInfo = mailInfoSearchService.findByUserId(userId);

		AccountSettingForm accountSettingForm = new AccountSettingForm();
		accountSettingForm.setDeleteFlag(account.getDeleteFlag());
		accountSettingForm.setUserId(userId);
		accountSettingForm.setPassword(account.getPassword());
		accountSettingForm.setFileEnclosureCharFlag(account.getFileEnclosureCharFlag());
		accountSettingForm.setHealthInfoMaskFlag(account.getHealthInfoMaskFlag());
		accountSettingForm.setMailAddress(mailInfo.getMailAddress());
		accountSettingForm.setMailPassword(mailInfo.getMailPassword());
		accountSettingForm.setRemarks(account.getRemarks());
		return accountSettingForm;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping(value = "/account-setting-input.html")
	public String input(Model model, HttpServletRequest request) throws AccountSettingException {
		return getView(ManageWebView.ACCOUNT_SETTING_INPUT);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@PostMapping(value = "/account-setting-confirm.html")
	public String confirm(Model model, @Valid AccountSettingForm form, BindingResult result) throws AccountSettingException {

		if (result.hasErrors()) {
			return getView(ManageWebView.ACCOUNT_SETTING_INPUT);
		}

		model.addAttribute("form", form);

		return getView(ManageWebView.ACCOUNT_SETTING_CONFIRM);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PostMapping(value = "/account-setting-complete.html")
	public String complete(Model model, AccountSettingForm form, HttpServletRequest request) throws AccountSettingException {

//		if (CodeManager.getInstance().isEquals(MainKey.FLAG, SubKey.TRUE, form.getDeleteFlag())) {
//			// アカウントを削除する場合
//			accountSettingService.deleteAccount(form);
//		}

		// アカウント情報を検索し、マージする
		Account befAccount = accountSearchService.findByUserId(form.getUserId());
		accountSettingService.mergeAccount(befAccount, form);

		// メール情報を検索し、マージする
		MailInfo befMailInfo = mailInfoSearchService.findByUserId(form.getUserId());
		accountSettingService.mergeMailInfo(befMailInfo, form);

		if (Objects.isNull(befMailInfo.getUserId())) {

			MailInfo mailInfo = accountSettingService.convertMailInfo(form);
			// メール情報を新規登録する
			mailInfoCreateService.create(mailInfo);
			// アカウント情報を更新する
			accountUpdateService.update(befAccount);

		} else {

			// 更新処理を行う
			accountSettingService.update(befAccount, befMailInfo);

		}

		return getView(ManageWebView.ACCOUNT_SETTING_COMPLETE);
	}

}
