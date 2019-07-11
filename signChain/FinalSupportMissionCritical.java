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
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.staffName=staffName;
	}

	@Override
	public BeltConveyer setNext(BeltConveyer next) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		this.next=next;// �����Ԃ��B
		return this.next;
	}

	public BeltConveyer setNext(){
		return this;
	}

	@Override
	public TroubleCode support(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return troubleCode;//�����Ԃ��B
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return resolve(troubleCode);// �������ł������Ŏc��̂��ׂĂ̖�������������B
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		this.tc=new TroubleCode(troubleCode.getTroubleMathCode());
		return troubleCode;// �Ƃ����ӋC���݂ō��ꂽ�������������A���Ԃ͉������Ȃ��Ŏ�����t���Ԃ������������B
	}

	@Override
	public void review() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		System.out.println(tc.toString());
	}

	public String toString(){
		return this.staffName;
	}

}
