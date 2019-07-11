/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 *
 * (-453   ⇒    (453N
 *
 */
public class NegativeNumberAfterOpenParenthesis extends Support{

	/**
	 *
	 */
	public NegativeNumberAfterOpenParenthesis(String staffName) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(staffName);
		this.si=new StrategicCompiler("(.*)(\\({1})(-{1})([0-9.]+)(.*)", "$1$2$4N$5");
	}
}
