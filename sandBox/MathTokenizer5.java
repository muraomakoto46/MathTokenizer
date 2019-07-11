package sandBox;

import java.io.IOException;
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
 * MathTokenizer4では
 *    7*-4
 *    24/-3
 *    4^-3
 *    が適切に処理できるようにすることを目標として作られた。
 *    結果、負数を表す - を あらかじめ Nに変換し、その負数の尻にNを取り付けられていれば対応できるようになった。
 *    7*-9 を 7*9N に変換しておいてほしい。
 *    -6.5/4 ならば 6.5N/4 に変換しておいてほしい。
 *下記の計算はできない。
 * 括弧が利用できません。
 *  (19-7)*13
 *
 *  35.23N-7.89N =-27.3999999... Double型の精度はとても高いけど、完璧ではない。
 **/
public class MathTokenizer5 {
	private String mathCode;
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		String query="2.66";
		MathTokenizer5 mtz = new MathTokenizer5(query);
		System.out.println(mtz.separate());
	}

	public MathTokenizer5(String mathCode){
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

		//無限が入力されたらどうするのか。

	}
	public Double separate(){
		Double result=Double.valueOf(0.0);
		/**
		 * 手渡されたmathCodeに どんな 演算子が含まれていますか。
		 * 演算子に応じて最も適切な命令を下しましょう。
		 *
		 * */
		try{
			if(mathCode.isEmpty()){
				throw new IllegalArgumentException("空文字列 引数エラー");
			}

		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
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
			System.out.println("----");
			//ただし、 -の直前に * / ^のいずれかがあったら分割しない。
			result = separate(this.mathCode,"-");
			return result;
		}

		// 97^150 % 13 のような計算をしなきゃならんから、 ％は ^よりも優先順位を低くしておく。
		if(mathCode.indexOf("%")!=-1){
			result = separate(this.mathCode,"%");
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


		if(mathCode.matches("^\\d+.{0,1}\\d*N$")){

			mathCode=mathCode.replaceAll("^(.*)(N)$", "$1");
			result = Double.parseDouble(mathCode);
			result = result * (-1);
			System.out.println("引数のないseparate()で、3.9NのようにN付き実数だと判定されました。");
			return result;
		}

		//↓これは最後に書かなければならない。
		/* mathCode.matches("^\\d+$") だと、小数点が間に含まれているものは反応しないやんか。*/
		if(mathCode.matches("^\\d+.{0,1}\\d*$")){
			//System.out.println("数値");
			return Double.parseDouble(mathCode);
		}

		if(mathCode.matches("^[e]{1}[N]{0,1}$")){
			Double e =Math.E;
			if(mathCode.matches("[e]{1}N{1}")){
				e=e*(-1);
			}
			return e;
		}

		if(mathCode.matches("^[P]{1}[N]{0,1}$")){
			Double pi= Math.PI;
			if(mathCode.matches("^[P]{1}[N]{1}$")){
				pi= pi*(-1);
			}
			return pi;
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
		System.out.println("code : "+code +"; tokenizer : "+ tokenizer);
		Double result=null;
		while(st.hasMoreTokens()){
			String prey=st.nextToken();
			System.out.println("prey:"+prey+";tokenizer:"+tokenizer+";code:"+code + "result="+result);
			if((result==null) && prey.matches("^(\\d+)([.]*)(\\d*)$")){
				result=Double.parseDouble(prey);

			}else if((result==null) && prey.matches("^(\\d+)([.]*)(\\d*)([N]{1})$")){
				prey=prey.replaceAll("^(.*)(N)$", "$1");
				result=Double.parseDouble(prey);
				result=result*(-1);
				System.out.println("3.9N のような数字がやってきたぞ。");
			}else{

				if(result==null){
					//左の枝の数式を分解。
					System.out.println("左の数式を分解 code:"+prey);
					result=(new MathTokenizer5(prey)).separate();
				}else{
					//右の枝分解。
					switch(tokenizer){
						case "+":
							result+=(new MathTokenizer5(prey)).separate();
							break;
						case "-":
							result-=(new MathTokenizer5(prey)).separate();
							break;
						case "*":
							result*=(new MathTokenizer5(prey)).separate();
							break;
						case "/":
							result/=(new MathTokenizer5(prey)).separate();
							break;
							//defalutを設けるとバグを発生させてしまう。
						case "^":
							result = Math.pow(result, (new MathTokenizer5(prey)).separate());
							break;
						case "%":
							Double reminder=(new MathTokenizer5(prey).separate());
							result = Math.IEEEremainder(result, reminder);
							if(result.compareTo(Double.valueOf("0"))<0){
								result=result*(-1);
								result = reminder-result;
							}
							break;
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
