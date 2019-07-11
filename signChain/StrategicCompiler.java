/**
 *
 */
package signChain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MuraoMakoto
 * 各種Supportの拡張クラスのcanIResolve()とresolve()では共通するアルゴリズムが出てくる
 * それと、各サブクラスとの差異は、引数と、正規表現のパターンだけだ。
 * そこでSupportのサブクラスのアルゴリズム部分を切り離して、Strategyデザインパターンを採用してみてはどうかと思うのだ。
 * コンストラクタの引数
 * String pattern,String replacement
 */
public class StrategicCompiler implements StrategicInterface{
	private String troubleMathCode;
	private String pattern;
	private String replacement;
	/**
	 *引数の String pattern について
	 *pattern = "(.*)([A-MO-Za-z]+)(.*)";
	 *のような正規表現のパターンを書いてください。
	 *String replacementについて
	 * "$1$2$3"
	 */
	public StrategicCompiler(String pattern,String replacement) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.pattern=pattern;
		this.replacement=replacement;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//StrategicCompiler sc = new StrategicCompiler();
		String pattern ="[a-z]+";
		String query =  "horser";
		Pattern pat =Pattern.compile(pattern);
		Matcher icchi = pat.matcher(query);
		System.out.println(icchi.find());
		System.out.println(pat.matcher(query).find());
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		this.troubleMathCode =troubleCode.getTroubleMathCode();

		if(Pattern.matches(this.pattern, troubleMathCode)){
			System.out.println("一致した");
			return this.resolve(troubleCode);
		}else{
			System.out.println("一致しなかった。");
			return troubleCode;
		}
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		this.troubleMathCode = troubleCode.getTroubleMathCode();

		while(Pattern.matches(this.pattern, this.troubleMathCode)){
			this.troubleMathCode = this.troubleMathCode.replaceFirst(this.pattern, this.replacement);
		}
		return new TroubleCode(this.troubleMathCode);
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return this.troubleMathCode;
	}

}
