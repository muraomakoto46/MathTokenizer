/**
 *
 */
package signChain;

import java.util.regex.Pattern;

/**
 * @author makoto
 *
 * ---7 = -7
 * +++7 = 7
 * 9++++ = 9
 * 5-- = 5
 * 5-  = 5-
 * 8--7 = 8+7
 * N
 * ���̃N���X�ł�+��-�̘A�������݂̂���������B
 * N�ϊ��͍s��Ȃ��B�������炷�������B
 *
 * 8-*+*-**+**-8�̂悤�Ɋ���Z��|���Z���܂܂�Ă���ꍇ�ɂ��Ă͂ق��ł���Ă��炤�B
 * ���[��+�ƉE�[��-���Ƃ��āA8+-8�Ƃ��āA 8+8N�ɂ���B
 * 8*****9
 */
public class SeveralOperatorSuccess extends Support{

	public SeveralOperatorSuccess(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String troubleMathCode = troubleCode.getTroubleMathCode();
		if(Pattern.matches("(.*)(\\+-|\\+\\+|--|-\\+)(.*)",troubleMathCode)){
			return resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String troubleMathCode=troubleCode.getTroubleMathCode();
		while(Pattern.matches("(.*)(\\+-|\\+\\+|--|-\\+)(.*)",troubleMathCode)){
			troubleMathCode = troubleMathCode.replaceAll("\\-{2}|\\+{2}", "+");
			troubleMathCode = troubleMathCode.replaceAll("\\+-|-\\+", "-");
		}
		this.tc = new TroubleCode(troubleMathCode);
		this.review();
		return tc;
	}
}
