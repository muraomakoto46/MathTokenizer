/**
 *
 */
package signChain;

/**
 * @author Murao Makoto
 * -(()) ⇒ (())N
 *
 * -で始まり、直後に括弧が来る場合のみサポートする。
 * -(4+5) ⇒ (4+5)N
 * -(4+6)-3 ⇒ (4+6)N-3
 * -(4+(5+7))-9 ⇒ (4+(5+7))N-9
 *文字列の一文字目が-で、その次が(である場合、
 *その(に対応する) の直後に Nを挿入し、
 *1文字目の-を消去する。
 */
public class LeftEdgeNegativeBeforeOpeningParenthesis extends Support implements BeltConveyer{
	public LeftEdgeNegativeBeforeOpeningParenthesis(String staffName){
		super(staffName);
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ

		String troubleMathCode = troubleCode.getTroubleMathCode();


		//System.out.println("match");
		/*
		 * int count =0;
		 * ((()))
		 * 左から右へ走査し、開きかっこが出たら、count++し、
		 * 閉じ括弧が登場したら count--し、
		 * countが0になったとき、その時の位置が Nを挿入すべき場所
		 * */
		int count=0;
		StringBuilder sb= new StringBuilder(troubleMathCode);

		for(int i=1;i<troubleMathCode.length();i++){
			if(sb.charAt(i)=='('){
				count++;
			}else if(sb.charAt(i)==')'){
				count--;
			}

			if(count==0){
				sb.insert(i+1, 'N');
				break;
			}
		}

		sb.deleteCharAt(0);
		troubleMathCode=sb.toString();

		super.tc = new TroubleCode(troubleMathCode);
		super.review();
		return tc;

	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		String troubleMathCode = troubleCode.getTroubleMathCode();
		if(troubleMathCode.matches("^(-{1})(\\({1})(.*)(\\){1})(.*)")){
			//うちの管轄だよ。
			return this.resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}
}
