/**
 *
 */
package signChain;

/**
 * @author makoto
 *
 */
public class ExponentialExpressionExchange extends Support implements BeltConveyer {

	/**
	 * @author MuraoMakoto
	 * -2.8E-14 を、 2.8N*10^14Nに変換してやる。
	 *
	 *
	 */
	private String pattern = "(.*)([\\d]{1})([\\.]{1})([\\d]+)([E]{1})([-]{0,1})([\\d]+)(.*)";
	public ExponentialExpressionExchange(String staffName) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(staffName);
	}

	/* (非 Javadoc)
	 * @see signChain.BeltConveyer#canIResolve(signChain.TroubleCode)
	 */
	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		if(troubleCode.toString().matches(pattern)){
			return resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}

	/* (非 Javadoc)
	 * @see signChain.BeltConveyer#resolve(signChain.TroubleCode)
	 */
	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO 自動生成されたメソッド・スタブ
		String trouble = troubleCode.getTroubleMathCode();
		String val = trouble.replaceFirst(pattern, "$1$2$3$4*10^$6$7$8");
		val = val.replaceFirst("(.*)([*]{1})([10]{2})([\\^]{1})([-]{1})([\\d]+)(.*)", "$1$2$3$4$6N$7");
		val = val.replaceFirst("([-]{1})([\\d\\.]+)([*]{1})(.+)","$2N$3$4");
		System.out.println("指数表現エンコード後：val="+val);
		super.tc = new TroubleCode(val);
		super.review();
		return tc;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		TroubleCode tc = new TroubleCode("4.98E28");
		ExponentialExpressionExchange eee = new ExponentialExpressionExchange("Mr.E");
		TroubleCode rs=eee.resolve(tc);
		System.out.println(" / result:"+rs.getTroubleMathCode());
	}

}
