package sandBox;

import java.util.StringTokenizer;

/**
 * 分割統治方式 文字列数式四則計算
 * @author Murao Makoto 2014/03/2
 *
 * google検索バーに、計算式を入れて検索ボタンを押すと、答えが表示される。
 * それとよく似た処理を行ってくれる。
 * googleはロードしなければ計算結果が出ないが、このクラスとJTextFieldを使えばすぐにでる。
 * このクラスでは 実数の四則計算をサポートする。
 * () は 使えません。
 * 再帰的に求めて上位にdouble値を返却しようとする。
 * 保守性と拡張性が認められないソースコードだ。
 *USAGE 使用法:
 *String mathCode="987/65-4*3+2-1"
 *MathTokenizer mt = new MathTokenizer(mathCode);
 *Double result = mt.separete();
 *System.out.println("result = " + result);
 *
 *
 *
 *次の計算ができる。
 * 2+3
 * 8-4
 * 1+2+3+4+5+6+7+8+9+10 この場合は、55が出力されるだろう。
 * 4+2*5 この場合は 4+(2*5) だとみなされる。
 * 2*6+9*7 この場合は(2*6)+(9*7) だとみなされる。
 * 64*2+4 この場合は (64*2)+4 だとみなされる。
 * 42.195*3.14+2.71
 *
 * エラー入力された場合も まあまあそれなりに対処してくれる。
 * 9+-2 この場合は 9-2 だとみなされる。
 * 9-+2 この場合は 9+2 だとみなされる。
 * 5++++++++6 この場合は 5+6だとみなされて、11になる。
 * 2*3/4*5 この場合は ((2*3)/4)*5 だとみなされて、 7.5になる。
 *
 * -4*8  この場合は、(0-(4*8)) だとみなされて、-32になる。
 *
 *下記の計算はできない。
 * -4*-5 この場合は、0-4-5だとみなされる。
 * -18/-3 この場合は 0-18-3 とみなされる。
 * -4^2 この場合は、= 0-(4^2) だとみなされる。
 *
 * 括弧が利用できません。
 *  (19-7)*13
 **/
public class MathTokenizer3 {
	private String mathCode;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String mathCode = "-4^2";
		MathTokenizer3 mt = new MathTokenizer3(mathCode);
		Double result = mt.separate();
		System.out.println("result = " + result);
	}

	public MathTokenizer3(String mathCode){
		this.mathCode=mathCode;
		if(mathCode.startsWith("-")){
			StringBuilder mending= new StringBuilder();
			mending.append("0");
			mending.append(mathCode);
			this.mathCode=mending.toString();
		}
		//mathCodeから 事前に空白文字などを除去しておく必要がある。

		//mathCodeが"" 0文字だったら 0を入力する。
		if(mathCode.length()==0){
			this.mathCode="0";
		}

	}
	public Double separate(){
		double result=0;
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

		// ^ で分ける
		if(mathCode.indexOf("^")!=-1){
			result = separate(this.mathCode,"^");
			return result;
		}

		//↓これは最後に書かなければならない。
		/* mathCode.matches("^\\d+$") だと、小数点が間に含まれているものは反応しないやんか。*/
		if(mathCode.matches("^\\d+.{0,1}\\d*$")){
			//System.out.println("数値");
			return Double.parseDouble(mathCode);
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
	private Double separate(String code,String tokenizer){
		StringTokenizer st = new StringTokenizer(code,tokenizer);//trueを設定するとおかしくなる。
		//System.out.println("code : "+code +"; tokenizer : "+ tokenizer);
		Double result=null;
		while(st.hasMoreTokens()){
			String prey=st.nextToken();
			//System.out.println("prey:"+prey+";tokenizer:"+tokenizer);
			if((result==null) && prey.matches("^\\d+$")){
				result=Double.parseDouble(prey);
			}else{
				if(result==null){
					//左の枝の数式を分解。
					result=(new MathTokenizer3(prey)).separate();
				}else{
					//右の枝分解。
					switch(tokenizer){
						case "+":
							result+=(new MathTokenizer3(prey)).separate();
							break;
						case "-":
							result-=(new MathTokenizer3(prey)).separate();
							break;
						case "*":
							result*=(new MathTokenizer3(prey)).separate();
							break;
						case "/":
							result/=(new MathTokenizer3(prey)).separate();
							break;
							//defalutを設けるとバグを発生させてしまう。
						case "^":
							Double exp =(new MathTokenizer3(prey)).separate();
							//System.out.println("MathPow直前 result = " + result+";exp = " + exp);
							result = Math.pow(result, exp);
							//System.out.println("MathPow直後 result = " + result);
							break;
						default:
							//System.out.println(tokenizer);
							break;
					}

				}

			}
		}



		return result;
	}
}
