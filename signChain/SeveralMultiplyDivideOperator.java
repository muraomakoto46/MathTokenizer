/**
 *
 */
package signChain;

import java.util.regex.Pattern;

/**
 * @author MuraoMakoto
 *
 *   ／*  ⇒ ／に変換することにする。
 *   *／  ⇒ * に変換することにする。
 *   *^  ⇒ *
 *   /^  ⇒ /
 *   ^*  ⇒ *
 *   ^/  ⇒ /
 *   ******* ⇒ *
 *   /////// ⇒ /
 *   ^^^^^^^ ⇒ ^
 *
 *   ^ は *や ／よりも優先順位が低い。
 */
public class SeveralMultiplyDivideOperator extends Support{

	public SeveralMultiplyDivideOperator(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		if(Pattern.matches(".*([*/]{2,}).*", troubleCode.getTroubleMathCode())){
			return this.resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		String troubleMathCode = troubleCode.getTroubleMathCode();
		while(Pattern.matches(".*([*/^]{2,}).*", troubleMathCode)){
			troubleMathCode = troubleMathCode.replaceAll("\\*{2,}", "\\*");
			troubleMathCode = troubleMathCode.replaceAll("\\/{2,}", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\^{2,}", "\\^");
			troubleMathCode = troubleMathCode.replaceAll("\\/\\*", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\*\\/", "\\*");
			troubleMathCode = troubleMathCode.replaceAll("\\*\\^", "\\*");
			troubleMathCode = troubleMathCode.replaceAll("\\/\\^", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\^\\/", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\^\\*", "\\*");
		}

		this.tc=new TroubleCode(troubleMathCode);
		this.review();
		return tc;
	}

}
