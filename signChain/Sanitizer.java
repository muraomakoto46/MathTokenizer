/**
 *
 */
package signChain;

/**
 * @author makoto
 *
 */
public class Sanitizer {
	private TroubleCode tc;
	/**
	 *
	 */
	public Sanitizer(String mathCode) {
		// TODO 自動生成されたコンストラクター・スタブ
		tc = new TroubleCode(mathCode);
	}

	public String sanitize(){
		String mathCode=tc.getTroubleMathCode();
		if(mathCode.matches("(.*)(Infinity)(.*)")){
			return "Infinity";
		}
		BeltConveyer window = new WindowEntrance("makiko");
		BeltConveyer expo = new ExponentialExpressionExchange("-2.71E-34 ⇒ -2.71*10^34N");
		BeltConveyer intrinsicNumberPI = new P_CircularConstant("P ⇒ 3.141592...");//定数文字を実数に変換
		BeltConveyer intrinsicNumberE  = new E_NeipiasStaticNumber("e ⇒ 2.71...");//定数文字を実数に変換
		BeltConveyer suppressPoint = new PointSurplus("42.......195 ⇒ 42.195");
		BeltConveyer hate = new AlphabetEliminator("123ABCabc45EfGh78 ⇒ 1234578");
		BeltConveyer cynical = new SeveralMultiplyDivideOperator("*****  |  /////");
		BeltConveyer brave = new SeveralOperatorSuccess("+-|--|-+");
		BeltConveyer startle = new MultiDivBeforeNegativeNumber("*- |  /-");
		BeltConveyer happy = new MultiDivBeforePositive("*+   |   /*");
		BeltConveyer miserable = new LeftEdgeNegativeSupport("|-");
		BeltConveyer solitary = new NegativeNumberAfterOpenParenthesis("(-34");
		BeltConveyer angry = new LeftEdgeNegativeBeforeOpeningParenthesis("-(");
		BeltConveyer preparation = new NegativeBetweenDivideORMultiAndOpeningParenthesis("  *-(  |  /-( ");
		BeltConveyer numneg = new NumNegNumChangeNumPlusNumNmark(" 334-222 ⇒ 334+222N ");
		BeltConveyer nnn    = new NNNMultipleOccurrence("245.43NN ⇒  245.43N");
		BeltConveyer spaceman = new SpaceEliminator("24 + 56⇒24+56");
		FinalSupportMissionCritical finalize = new FinalSupportMissionCritical("everything");//ターミネーター


		window.setNext(expo);
		expo.setNext(intrinsicNumberPI);
		intrinsicNumberPI.setNext(intrinsicNumberE);
		intrinsicNumberE.setNext(suppressPoint);
		suppressPoint.setNext(hate);
		hate.setNext(cynical);
		cynical.setNext(brave);
		brave.setNext(startle);
		startle.setNext(happy);
		happy.setNext(miserable);
		miserable.setNext(solitary);
		solitary.setNext(angry);
		angry.setNext(preparation);
		preparation.setNext(numneg);
		numneg.setNext(nnn);
		nnn.setNext(spaceman);
		spaceman.setNext(finalize);
		finalize.setNext();




		//System.out.println("review = " + tc.getTroubleMathCode());
		TroubleCode resultCode = window.support(tc);
		//System.out.println("result = " + resultCode.getTroubleMathCode());

		return resultCode.getTroubleMathCode();
	}

	public String toString(){
		return tc.getTroubleMathCode();
	}

	public static void main(String[] args){
		String mathCode="-2.71E-34";
		Sanitizer sn=new Sanitizer(mathCode);
		System.out.println(sn.sanitize());
	}
}
