/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 * サポート窓口を表す。
 * WindowEntrance → そのほかのサポートセンター
 * なにもせずそのまま流す。
 */
public class WindowEntrance extends Support{

	public WindowEntrance(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		return troubleCode;
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		return new TroubleCode(troubleCode.getTroubleMathCode());
	}

	/**
	 *
	 */

}
