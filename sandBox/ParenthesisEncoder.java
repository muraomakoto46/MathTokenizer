/**
 *
 */
package sandBox;

import java.util.regex.Pattern;

import signChain.Sanitizer;

/**
 * @author MuraoMakoto
 * 数式にかっこがある場合はその括弧を外しながら計算してくれる。
 * 括弧がなければMathTokenizer4のインスタンスを作成して、そのインスタンスに計算させる。
 *
 */
public class ParenthesisEncoder {
	private String query;
	/**
	 *
	 */
	public ParenthesisEncoder(String query) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.query = query;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String query = "(-3  +   6)";
		ParenthesisEncoder pe = new ParenthesisEncoder(query);
		System.out.println(pe.encodeMathCode());
	}


	public String encodeMathCode(){
		//String query = "((7/3)-9)+(5*(41-2))";
		if(query.matches("(.*)(Infinity)(.*)")){
			return "Infinity";
		}
		this.query = new Sanitizer(this.query).sanitize();
		System.out.println("最初のサニタイズ終了後の文字列："+this.query);
		StringBuilder sb = new StringBuilder(this.query);
		String result= this.query;
		int openPosition=0;
		int closePosition=0;

		if(this.canEncode(query)){

			do{
				for(int i=0;i<sb.length();i++){
					if(sb.charAt(i) == '('){
						openPosition=i;
					}
					if(sb.charAt(i) == ')'){
						closePosition=i;
						break;
					}
				}

				String subString = sb.substring(openPosition+1, closePosition);
				System.out.println("ParenthesisEncoder#substring = " + subString);
				Sanitizer san =new Sanitizer(subString);
				System.out.println("サニタイズ後："+san.toString());
				String response =String.valueOf(new MathTokenizer5(san.sanitize()).separate());
				sb.replace(openPosition, closePosition+1, response);
				// 3N*(-3)⇒ 3N*-3 ←こんな文字列をもう一度サニタイズしなければならん。
				System.out.println("sb.toString() = "+sb.toString());
				san = new Sanitizer(sb.toString());
				sb = new StringBuilder(san.sanitize());
				result=sb.toString();
				System.out.println("result = "+result);

			}while(result.matches("(.*)([(]+)(.*)([)]+)(.*)"));
			Sanitizer san = new Sanitizer(result);
			if(result.matches("(.*)(Infinity)(.*)")){
				return "Infinity";
			}
			Double consequence = new MathTokenizer5(san.sanitize()).separate();
			result = consequence.toString();
		}else{
			this.query= new Sanitizer(this.query).sanitize();
			result=String.valueOf(new MathTokenizer5(this.query).separate());
		}

		return result;
	}

	public Boolean canEncode(String string){
		int occurrence=0;//←これが 0でなかったら、おかしい。
		for(int i=0;i<string.length();i++){
			if(string.charAt(i) == '('){
				occurrence++;
			}
			if(string.charAt(i) == ')'){
				occurrence--;
			}
		}

		if(occurrence==0 && Pattern.matches("(.*)([()]+)(.*)", string)){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}
}
