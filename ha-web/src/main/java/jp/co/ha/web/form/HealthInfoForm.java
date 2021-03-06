package jp.co.ha.web.form;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import jp.co.ha.common.web.BaseForm;

/**
 * 健康情報入力画面フォームクラス<br>
 *
 */
public class HealthInfoForm implements BaseForm {

	/** ユーザID */
	private String userId;
	/** 身長 */
	@Min(value = 1, message = "身長が桁数不足です")
	@Max(value = 999, message = "身長が桁数超過です")
	private BigDecimal height;
	/** 体重 */
	@Min(value = 1, message = "体重が桁数不足です")
	@Max(value = 999, message = "体重が桁数超過です")
	private BigDecimal weight;
	/** BMI */
	private BigDecimal bmi;
	/** 標準体重 */
	private BigDecimal standardWeight;

	/**
	 * userIdを返す
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userIdを設定する
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * heightを返す
	 * @return height
	 */
	public BigDecimal getHeight() {
		return height;
	}
	/**
	 * heightを設定する
	 * @param height
	 */
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	/**
	 * weightを返す
	 * @return weight
	 */
	public BigDecimal getWeight() {
		return weight;
	}
	/**
	 * weightを設定する
	 * @param weight
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	/**
	 * bmiを返す
	 * @return bmi
	 */
	public BigDecimal getBmi() {
		return bmi;
	}
	/**
	 * bmiを設定する
	 * @param bmi
	 */
	public void setBmi(BigDecimal bmi) {
		this.bmi = bmi;
	}
	/**
	 * standardWeightを返す
	 * @return standardWeight
	 */
	public BigDecimal getStandardWeight() {
		return standardWeight;
	}
	/**
	 * standardWeightを設定する
	 * @param standardWeight
	 */
	public void setStandardWeight(BigDecimal standardWeight) {
		this.standardWeight = standardWeight;
	}

}
