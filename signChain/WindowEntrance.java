/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 * �T�|�[�g������\���B
 * WindowEntrance �� ���̂ق��̃T�|�[�g�Z���^�[
 * �Ȃɂ��������̂܂ܗ����B
 */
public class WindowEntrance extends Support{

	public WindowEntrance(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return troubleCode;
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return new TroubleCode(troubleCode.getTroubleMathCode());
	}

	/**
	 *
	 */

}
