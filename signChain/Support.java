package signChain;
/**
 * Chain of Responsibility
 * 責任のタライ回しパターン
 * を応用して、
 * 下町工場のベルトコンベヤーパターンを作成した。
 * 責任のタライ回しパターンだと、誰かが解決したらそこで終わりで、
 * 次の人に仕事を依頼しない。
 * でも、下町工場のベルトコンベヤーパターンでは、解決してもしなくても必ず次に回す。
 *
 * これをファクトリーパターンと <T> ジェネリクスを用いて 一般化できないか。
 * */
public abstract class Support implements BeltConveyer{
	private String staffName;
	private BeltConveyer next;
	protected TroubleCode tc;
	protected StrategicInterface si;
	public Support(String staffName){
		this.staffName=staffName;
	}

	public BeltConveyer setNext(BeltConveyer next){
		this.next=next;
		return this.next;
	}

	public final TroubleCode support(TroubleCode troubleCode){
		//自分のところで処理できることだけはしっかりやってから、次の工場へ製品を引き渡す。
		return next.support(this.canIResolve(troubleCode));
	}

	public String toString(){
		return this.staffName;
	}

	/*うちで扱える問題なのかどうかを判定する。
	 * うちで加工すべきなら、return canIResolve()をする
	 * そうでないなら、引数をそのままreturn する。*/
	public TroubleCode canIResolve(TroubleCode troubleCode){
		return this.si.canIResolve(troubleCode);
	}

	//canIResolve()の中で使われる。
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		this.tc=this.si.resolve(troubleCode);
		this.review();
		return this.tc;
	}

	public void review(){
		System.out.println("SupportStaffName = "+this.staffName +"; review = " + tc.getTroubleMathCode());
	}
}
