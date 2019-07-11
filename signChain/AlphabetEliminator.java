/**
 *
 */
package signChain;

import java.util.regex.Pattern;

/**
 * @author MuraoMakoto
 *
 */
public class AlphabetEliminator extends Support{
	public AlphabetEliminator(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
		//this.si = new StrategicCompiler("(.*)([A-Ma-zO-Z]+)(.*)","$1$3");
		this.si = new StrategicCompiler("(.*)([A-M|Q-Z|a-d|f-z]+)(.*)","$1$3");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String query ="123abc+233N";
		String pattern ="(.*)([A-M|O-Z|a-z]+)(.*)";
		if(Pattern.matches(pattern, query)){
			System.out.println("一致した");
		}else{
			System.out.println("一致しなかった。");
		}

		while(Pattern.matches(pattern, query)){
			query = query.replaceFirst(pattern, "$1$3");
		}
		System.out.println("query = " + query);
	}
}
