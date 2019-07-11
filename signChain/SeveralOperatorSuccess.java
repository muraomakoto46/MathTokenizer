/**
 *
 */
package signChain;

import java.util.regex.Pattern;

/**
 * @author makoto
 *
 * ---7 = -7
 * +++7 = 7
 * 9++++ = 9
 * 5-- = 5
 * 5-  = 5-
 * 8--7 = 8+7
 * N
 * このクラスでは+と-の連続部分のみを処理する。
 * N変換は行わない。数を減らすだけだ。
 *
 * 8-*+*-**+**-8のように割り算や掛け算が含まれている場合についてはほかでやってもらう。
 * 左端の+と右端の-をとって、8+-8として、 8+8Nにする。
 * 8*****9
 */
public class SeveralOperatorSuccess extends Support{

	public SeveralOperatorSuccess(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		String troubleMathCode = troubleCode.getTroubleMathCode();
		if(Pattern.matches("(.*)(\\+-|\\+\\+|--|-\\+)(.*)",troubleMathCode)){
			return resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		String troubleMathCode=troubleCode.getTroubleMathCode();
		while(Pattern.matches("(.*)(\\+-|\\+\\+|--|-\\+)(.*)",troubleMathCode)){
			troubleMathCode = troubleMathCode.replaceAll("\\-{2}|\\+{2}", "+");
			troubleMathCode = troubleMathCode.replaceAll("\\+-|-\\+", "-");
		}
		this.tc = new TroubleCode(troubleMathCode);
		this.review();
		return tc;
	}
}
