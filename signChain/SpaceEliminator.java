/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 * �����񂩂�󔒂���������B
 */
public class SpaceEliminator extends Support{
	public SpaceEliminator(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.si=new StrategicCompiler("^(.*)(\\p{Space}+)(.*)$","$1$3");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
//		String trouble = "12    -  334 +      556";

//		if(Pattern.matches("(.*)(\\p{Space})(.*)", trouble)){
//			System.out.println("�}�b�`����");
//		}else{
//			System.out.println("�}�b�`���Ȃ������B");
//		}

//		SpaceEliminator su=new SpaceEliminator("space man");
//		TroubleCode query = new TroubleCode(trouble);
//		TroubleCode gain = su.canIResolve(query);
//		System.out.println("gain = " + gain.getTroubleMathCode());
	}

}
