/**
 *
 */
package signChain;

/**
 * @author Murao Makoto
 * -(()) �� (())N
 *
 * -�Ŏn�܂�A����Ɋ��ʂ�����ꍇ�̂݃T�|�[�g����B
 * -(4+5) �� (4+5)N
 * -(4+6)-3 �� (4+6)N-3
 * -(4+(5+7))-9 �� (4+(5+7))N-9
 *������̈ꕶ���ڂ�-�ŁA���̎���(�ł���ꍇ�A
 *����(�ɑΉ�����) �̒���� N��}�����A
 *1�����ڂ�-����������B
 */
public class LeftEdgeNegativeBeforeOpeningParenthesis extends Support implements BeltConveyer{
	public LeftEdgeNegativeBeforeOpeningParenthesis(String staffName){
		super(staffName);
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

		String troubleMathCode = troubleCode.getTroubleMathCode();


		//System.out.println("match");
		/*
		 * int count =0;
		 * ((()))
		 * ������E�֑������A�J�����������o����Acount++���A
		 * �����ʂ��o�ꂵ���� count--���A
		 * count��0�ɂȂ����Ƃ��A���̎��̈ʒu�� N��}�����ׂ��ꏊ
		 * */
		int count=0;
		StringBuilder sb= new StringBuilder(troubleMathCode);

		for(int i=1;i<troubleMathCode.length();i++){
			if(sb.charAt(i)=='('){
				count++;
			}else if(sb.charAt(i)==')'){
				count--;
			}

			if(count==0){
				sb.insert(i+1, 'N');
				break;
			}
		}

		sb.deleteCharAt(0);
		troubleMathCode=sb.toString();

		super.tc = new TroubleCode(troubleMathCode);
		super.review();
		return tc;

	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String troubleMathCode = troubleCode.getTroubleMathCode();
		if(troubleMathCode.matches("^(-{1})(\\({1})(.*)(\\){1})(.*)")){
			//�����̊Ǌ�����B
			return this.resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}
}
