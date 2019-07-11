/**
 *
 */
package signChain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MuraoMakoto
 * �e��Support�̊g���N���X��canIResolve()��resolve()�ł͋��ʂ���A���S���Y�����o�Ă���
 * ����ƁA�e�T�u�N���X�Ƃ̍��ق́A�����ƁA���K�\���̃p�^�[���������B
 * ������Support�̃T�u�N���X�̃A���S���Y��������؂藣���āAStrategy�f�U�C���p�^�[�����̗p���Ă݂Ă͂ǂ����Ǝv���̂��B
 * �R���X�g���N�^�̈���
 * String pattern,String replacement
 */
public class StrategicCompiler implements StrategicInterface{
	private String troubleMathCode;
	private String pattern;
	private String replacement;
	/**
	 *������ String pattern �ɂ���
	 *pattern = "(.*)([A-MO-Za-z]+)(.*)";
	 *�̂悤�Ȑ��K�\���̃p�^�[���������Ă��������B
	 *String replacement�ɂ���
	 * "$1$2$3"
	 */
	public StrategicCompiler(String pattern,String replacement) {
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.pattern=pattern;
		this.replacement=replacement;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		//StrategicCompiler sc = new StrategicCompiler();
		String pattern ="[a-z]+";
		String query =  "horser";
		Pattern pat =Pattern.compile(pattern);
		Matcher icchi = pat.matcher(query);
		System.out.println(icchi.find());
		System.out.println(pat.matcher(query).find());
	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		this.troubleMathCode =troubleCode.getTroubleMathCode();

		if(Pattern.matches(this.pattern, troubleMathCode)){
			System.out.println("��v����");
			return this.resolve(troubleCode);
		}else{
			System.out.println("��v���Ȃ������B");
			return troubleCode;
		}
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		this.troubleMathCode = troubleCode.getTroubleMathCode();

		while(Pattern.matches(this.pattern, this.troubleMathCode)){
			this.troubleMathCode = this.troubleMathCode.replaceFirst(this.pattern, this.replacement);
		}
		return new TroubleCode(this.troubleMathCode);
	}

	@Override
	public String toString() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return this.troubleMathCode;
	}

}
