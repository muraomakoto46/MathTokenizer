/**
 *
 */
package signChain;

/**
 * @author makoto
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		BeltConveyer window = new WindowEntrance("makiko");
		BeltConveyer cynical = new SeveralMultiplyDivideOperator("*****  |  /////");
		BeltConveyer brave = new SeveralOperatorSuccess("+-|--|-+");
		BeltConveyer startle = new MultiDivBeforeNegativeNumber("*- |  /-");
		BeltConveyer happy = new MultiDivBeforePositive("*+   |   /*");
		BeltConveyer miserable = new LeftEdgeNegativeSupport("|-");
		BeltConveyer solitary = new NegativeNumberAfterOpenParenthesis("(-34");
		BeltConveyer angry = new LeftEdgeNegativeBeforeOpeningParenthesis("-(");
		BeltConveyer preparation = new NegativeBetweenDivideORMultiAndOpeningParenthesis("  *-(  |  /-( ");

		FinalSupportMissionCritical finalize = new FinalSupportMissionCritical("everything");//ターミネーター


		window.setNext(cynical);
		cynical.setNext(brave);
		brave.setNext(startle);
		startle.setNext(happy);
		happy.setNext(miserable);
		miserable.setNext(solitary);
		solitary.setNext(angry);
		angry.setNext(preparation);
		preparation.setNext(finalize);
		finalize.setNext();

		TroubleCode tc =new TroubleCode("9******678*////5-4*-(-3503.323/-553+(-87))/-(553/24)--(8+7)");
		System.out.println("review = " + tc.getTroubleMathCode());
		TroubleCode resultCode = window.support(tc);
		System.out.println("result = " + resultCode.getTroubleMathCode());
	}
}
