package sandBox;

import java.util.StringTokenizer;

/**
 * 文字列数式演算
 * 再帰的に求めて上位にlong値を返却しようとする。
 * 処理結果を求めるための変数と 資料とが交雑していない。
 * 保守性と拡張性が認められないソースコードだ。
 *USAGE 使用法:
 *String mathCode="987/65-4*3+2-1"
 *MathTokenizer mt = new MathTokenizer(mathCode);
 *long result = mt.separete();
 *System.out.println("result = " + result);*/
public class MathTokenizer2 {
	private String mathCode;

	public MathTokenizer2(String mathCode){
		this.mathCode=mathCode;
		//mathCodeから 事前に空白文字などを除去しておく必要がある。
	}
	public long separate(){
		long result=0;
		/**
		 * 手渡されたmathCodeに どんな 演算子が含まれていますか。
		 * 演算子に応じて最も適切な命令を下しましょう。
		 *
		 * */
		if(mathCode.isEmpty()){
			throw new IllegalArgumentException("空文字列 引数エラー");
		}

		// + と - で分けるほうを * や /で分けるよりも 優先しなければならない。
		//↓これらのif文の 呼び出す順序は はっきりと決まっている。

		// + で分ける


		// 正規表現を使うという手段もあるけど。 C&R研究所著 Java逆引きハンドブック 286ページ参照
		if(mathCode.indexOf("+")!=-1){
			//System.out.println("++++");
			result = separate(this.mathCode,"+");
			return result;
		}

		// - で分ける
		if(mathCode.indexOf("-")!=-1){
			//System.out.println("----");
			result = separate(this.mathCode,"-");
			return result;
		}

		// * で分ける
		if(mathCode.indexOf("*")!=-1){
			result = separate(this.mathCode,"*");
			return result;
		}
		// / で分ける。
		if(mathCode.indexOf("/")!=-1){
			result = separate(this.mathCode,"/");
			return result;
		}

		//↓これは最後に書かなければならない。
		if(mathCode.matches("^\\d+$")){
			return Long.parseLong(mathCode);
		}


		return result;//ここで返されることはない。
	}

	/**このやり方だと 3×3-3が うまくいかへん
	 * 3+3*3 だとうまくいく。
	 * 3*3+3 OK
	 * 3+3+3 OK
	 * 4+3*3+4 OK
	 * 10-2-3 OK
	 * 4*5*6 OK
	 * 60/2/3 OK
	 * 50/2+15 OK
	 * 5+5-3 OK
	 * 2+3*4+567/8+9*10 OK
	 * 10-5-2 OK
	 * 5*5-3*3 NG -34になった。うまくいってない。
	 * -7 NG +7になった。
	 * 8-5 を 8 + (-5) だと みなせると楽なのだが。
	 * -4 + 5 = (-4) + 5
	 * -8 - 7 = (-8) + (-7)
	 * 2+3*4+567/8-9*10 =(2)+(3*4)+(567/8-9*10) = (2)+(3*4)+((567/8)+(-9*10))
	 * @throws Exception */
	private long separate(String code,String tokenizer){
		StringTokenizer st = new StringTokenizer(code,tokenizer);
		//System.out.println("code : "+code +"; tokenizer : "+ tokenizer);
		Long result=null;
		while(st.hasMoreTokens()){
			String prey=st.nextToken();
			//System.out.println("prey:"+prey+";tokenizer:"+tokenizer);
			if((result==null) && prey.matches("^\\d+$")){
				result=Long.valueOf(prey);
			}else{
				if(result==null){
					result=(new MathTokenizer2(prey)).separate();
				}else{
					//ここに枝の左右に応じて 負数処理をアシストできればいいのだが。
					switch(tokenizer){
						case "+":
							result+=(new MathTokenizer2(prey)).separate();
							break;
						case "-":
							result-=(new MathTokenizer2(prey)).separate();
							break;
						case "*":
							result*=(new MathTokenizer2(prey)).separate();
							break;
						case "/":
							result/=(new MathTokenizer2(prey)).separate();
							break;
							//defalutを設けるとバグを発生させてしまう。
						default:
							System.out.println(tokenizer);
							break;
					}

				}

			}
		}



		return result;
	}
}
