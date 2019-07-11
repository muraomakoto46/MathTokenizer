package signChain;
/**
 * Chain of Responsibility
 * �ӔC�̃^���C�񂵃p�^�[��
 * �����p���āA
 * �����H��̃x���g�R���x���[�p�^�[�����쐬�����B
 * �ӔC�̃^���C�񂵃p�^�[�����ƁA�N�������������炻���ŏI���ŁA
 * ���̐l�Ɏd�����˗����Ȃ��B
 * �ł��A�����H��̃x���g�R���x���[�p�^�[���ł́A�������Ă����Ȃ��Ă��K�����ɉ񂷁B
 *
 * ������t�@�N�g���[�p�^�[���� <T> �W�F�l���N�X��p���� ��ʉ��ł��Ȃ����B
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
		//�����̂Ƃ���ŏ����ł��邱�Ƃ����͂����������Ă���A���̍H��֐��i�������n���B
		return next.support(this.canIResolve(troubleCode));
	}

	public String toString(){
		return this.staffName;
	}

	/*�����ň�������Ȃ̂��ǂ����𔻒肷��B
	 * �����ŉ��H���ׂ��Ȃ�Areturn canIResolve()������
	 * �����łȂ��Ȃ�A���������̂܂�return ����B*/
	public TroubleCode canIResolve(TroubleCode troubleCode){
		return this.si.canIResolve(troubleCode);
	}

	//canIResolve()�̒��Ŏg����B
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		this.tc=this.si.resolve(troubleCode);
		this.review();
		return this.tc;
	}

	public void review(){
		System.out.println("SupportStaffName = "+this.staffName +"; review = " + tc.getTroubleMathCode());
	}
}
