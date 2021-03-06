package jp.co.ha.web.file.csv.writer;

import java.util.List;
import java.util.StringJoiner;

import jp.co.ha.business.parameter.ParamConst;
import jp.co.ha.common.file.csv.CsvConfig;
import jp.co.ha.common.file.csv.writer.BaseCsvWriter;
import jp.co.ha.common.util.CsvUtil;
import jp.co.ha.common.util.DateFormatDefine;
import jp.co.ha.common.util.DateUtil;
import jp.co.ha.common.util.StringUtil;
import jp.co.ha.web.file.csv.model.ReferenceCsvModel;

/**
 * 結果照会CSVWriterクラス<br>
 *
 */
public class ReferenceCsvWriter extends BaseCsvWriter<ReferenceCsvModel> {

	/**
	 * コンストラクタ<br>
	 * @param conf CSV設定情報
	 * @param modelList CSVモデルリスト
	 */
	public ReferenceCsvWriter(CsvConfig conf, List<ReferenceCsvModel> modelList) {
		super(conf, modelList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeHeader(StringJoiner recordJoiner) {

		StringJoiner joiner = new StringJoiner(StringUtil.COMMA);
		CsvUtil.getHeaderList(ReferenceCsvModel.class).stream().forEach(headerName -> write(joiner, headerName));
		recordJoiner.add(joiner.toString());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeData(StringJoiner recordJoiner, ReferenceCsvModel model) {

		// 1項目ごと区切る
		StringJoiner joiner = new StringJoiner(StringUtil.COMMA);

		// ユーザID
		write(joiner, model.getUserId());
		// 身長
		write(joiner, model.getHeight().toString());
		// 体重
		write(joiner, model.getWeight().toString());
		// BMI
		write(joiner, model.getBmi().toString());
		// 標準体重
		write(joiner, model.getStandardWeight().toString());
		// 登録日時
		write(joiner, DateUtil.toString(model.getRegDate(), DateFormatDefine.YYYYMMDD_HHMMSS));

		// 1行書き込む
		recordJoiner.add(joiner.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getFileName() {
		return ParamConst.CSV_FILE_NAME_REFERNCE_RESULT.getValue();
	}

}
