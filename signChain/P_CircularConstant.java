/**
 * 
 */
package signChain;

/**
 * @author makoto
 * P=
 * 3.14
 */
public class P_CircularConstant extends Support{

	public P_CircularConstant(String staffName) {
		super(staffName);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		this.si=new StrategicCompiler("^(.*)([P]{1})(.*)$","$13.1415926535897932384626433832795$3");
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
			query = query.replaceFirst(pattern,"$13.1415926535897932384626433832795$3");
		}

		System.out.println(query);
	}

}
