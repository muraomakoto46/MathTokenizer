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
	 * -2.8E-14 ���A 2.8N*10^14N�ɕϊ����Ă��B
	 *
	 *
	 */
	private String pattern = "(.*)([\\d]{1})([\\.]{1})([\\d]+)([E]{1})([-]{0,1})([\\d]+)(.*)";
	public ExponentialExpressionExchange(String staffName) {
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		super(staffName);
	}

	/* (�� Javadoc)
	 * @see signChain.BeltConveyer#canIResolve(signChain.TroubleCode)
	 */
	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		if(troubleCode.toString().matches(pattern)){
			return resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}

	/* (�� Javadoc)
	 * @see signChain.BeltConveyer#resolve(signChain.TroubleCode)
	 */
	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String trouble = troubleCode.getTroubleMathCode();
		String val = trouble.replaceFirst(pattern, "$1$2$3$4*10^$6$7$8");
		val = val.replaceFirst("(.*)([*]{1})([10]{2})([\\^]{1})([-]{1})([\\d]+)(.*)", "$1$2$3$4$6N$7");
		val = val.replaceFirst("([-]{1})([\\d\\.]+)([*]{1})(.+)","$2N$3$4");
		System.out.println("�w���\���G���R�[�h��Fval="+val);
		super.tc = new TroubleCode(val);
		super.review();
		return tc;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		TroubleCode tc = new TroubleCode("4.98E28");
		ExponentialExpressionExchange eee = new ExponentialExpressionExchange("Mr.E");
		TroubleCode rs=eee.resolve(tc);
		System.out.println(" / result:"+rs.getTroubleMathCode());
	}

}
