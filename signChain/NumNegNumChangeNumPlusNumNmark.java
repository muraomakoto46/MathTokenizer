/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 *
 * ���ׂ��� ���K�\���̃p�^�[���ƃ��v���[�X�p�^�[�������������ݒ肵�Ȃ��Ă������悤�ɂȂ��Ă���B
 * ���O�ɐݒ肵�Ă����āA���̐ݒ�ɖ��O���t���Ă����񂾁B
 * ��������� ���K�\�����C�ɂ��Ȃ��ł�����g�������� �T�j�^�C�W���O���ł���悤�ɂȂ�񂾁B
 *
 */
public class NumNegNumChangeNumPlusNumNmark extends Support{

	public NumNegNumChangeNumPlusNumNmark(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.si = new StrategicCompiler("(.*)([0-9.]+)([-]{1})([0-9.]+)(.*)", "$1$2\\+$4N$5");
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO �����������ꂽ���\�b�h�E�X�^�u
//
//		String trouble = "-((1/3)-3)";
//		if(trouble.matches("(.*)([0-9.]+)([-]{1})([0-9.]+)(.*)")){
//			System.out.println("match");
//		}else{
//			System.out.println("��v���Ȃ������B");
//		}
//
//
//		while(Pattern.matches("(.*)([0-9.]+)([-]{1})([0-9.]+)(.*)",trouble)){
//			trouble = trouble.replaceFirst("(.*)([0-9.]+)([-]{1})([0-9.]+)(.*)", "$1$2\\+$4N$5");
//		}
//
//		System.out.println(trouble);
//	}
}
