/**
 *
 */
package signChain;

/**
 * @author makoto
 *e=
 *2.7182818284590452353602874713527
 */
public class E_NeipiasStaticNumber extends Support {

	public E_NeipiasStaticNumber(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.si=new StrategicCompiler("^(.*)([e]{1})(.*)$","$12.7182818284590452353602874713527$3");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String query ="123*e*456";
		String pattern = "^(.*)([e]{1})(.*)$";
		if(query.matches(pattern)){
			System.out.println("match");
		}else{
			System.out.println("��v���Ȃ������B");
		}

		while(query.matches(pattern)){
			query = query.replaceFirst(pattern,"$12.7182818284590452353602874713527$3");
		}

		System.out.println(query);
	}

}
