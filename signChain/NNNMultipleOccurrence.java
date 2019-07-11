/**
 *
 */
package signChain;

/**
 * @author makoto
 * -((1/3)-3)
 * =-((0.33)-3)
 * =-(0.33-3)
 * =-(0.33+3N)
 * =-(2.66N)
 * ==2.66NN
 * �����
 * 2.66NN
 * ����������BN�͍ő�ł�1�A����������Ȃ��B
 */
public class NNNMultipleOccurrence extends Support{

	public NNNMultipleOccurrence(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.si=new StrategicCompiler("(.*)([0-9.]+)([N]{2})(.*)", "$1$2$4");
	}

	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String trouble = "2.66NNNNNNNNNNNN+234";
		Support nnn = new NNNMultipleOccurrence("anzof");
		TroubleCode tc=new TroubleCode(trouble);
		tc=nnn.resolve(tc);

	}
}
