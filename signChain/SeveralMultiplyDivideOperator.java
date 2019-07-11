/**
 *
 */
package signChain;

import java.util.regex.Pattern;

/**
 * @author MuraoMakoto
 *
 *   �^*  �� �^�ɕϊ����邱�Ƃɂ���B
 *   *�^  �� * �ɕϊ����邱�Ƃɂ���B
 *   *^  �� *
 *   /^  �� /
 *   ^*  �� *
 *   ^/  �� /
 *   ******* �� *
 *   /////// �� /
 *   ^^^^^^^ �� ^
 *
 *   ^ �� *�� �^�����D�揇�ʂ��Ⴂ�B
 */
public class SeveralMultiplyDivideOperator extends Support{

	public SeveralMultiplyDivideOperator(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		if(Pattern.matches(".*([*/]{2,}).*", troubleCode.getTroubleMathCode())){
			return this.resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String troubleMathCode = troubleCode.getTroubleMathCode();
		while(Pattern.matches(".*([*/^]{2,}).*", troubleMathCode)){
			troubleMathCode = troubleMathCode.replaceAll("\\*{2,}", "\\*");
			troubleMathCode = troubleMathCode.replaceAll("\\/{2,}", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\^{2,}", "\\^");
			troubleMathCode = troubleMathCode.replaceAll("\\/\\*", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\*\\/", "\\*");
			troubleMathCode = troubleMathCode.replaceAll("\\*\\^", "\\*");
			troubleMathCode = troubleMathCode.replaceAll("\\/\\^", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\^\\/", "\\/");
			troubleMathCode = troubleMathCode.replaceAll("\\^\\*", "\\*");
		}

		this.tc=new TroubleCode(troubleMathCode);
		this.review();
		return tc;
	}

}
