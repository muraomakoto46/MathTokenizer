/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 *
 * ／＋ ⇒ ／
 * ＊＋ ⇒ ＊
 *
 */
public class MultiDivBeforePositive extends Support{
	/**
	 *
	 */
	public MultiDivBeforePositive(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
		this.si=new StrategicCompiler("(.*)([*/]{1})(\\+{1})(.*)","$1$2$4");
	}
}
