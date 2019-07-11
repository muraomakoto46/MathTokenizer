/**
 *
 */
package signChain;

/**
 * @author makoto
 *
 */
public class FinalSupportMissionCritical implements BeltConveyer {
	private String staffName;
	private BeltConveyer next;
	protected TroubleCode tc;
	/**
	 *
	 */
	public FinalSupportMissionCritical(String staffName) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.staffName=staffName;
	}

	@Override
	public BeltConveyer setNext(BeltConveyer next) {
		// TODO 自動生成されたメソッド・スタブ
		this.next=next;// 引き返す。
		return this.next;
	}

	public BeltConveyer setNext(){
		return this;
	}

	@Override
	public TroubleCode support(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		return troubleCode;//引き返す。
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		return resolve(troubleCode);// 何が何でもここで残りのすべての問題を解決させる。
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		this.tc=new TroubleCode(troubleCode.getTroubleMathCode());
		return troubleCode;// という意気込みで作られた部署だったが、実態は何もしないで資料を付き返すだけだった。
	}

	@Override
	public void review() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(tc.toString());
	}

	public String toString(){
		return this.staffName;
	}

}
