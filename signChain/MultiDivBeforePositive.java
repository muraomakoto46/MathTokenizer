/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 *
 * �^�{ �� �^
 * ���{ �� ��
 *
 */
public class MultiDivBeforePositive extends Support{
	/**
	 *
	 */
	public MultiDivBeforePositive(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.si=new StrategicCompiler("(.*)([*/]{1})(\\+{1})(.*)","$1$2$4");
	}
}
