/**
 *
 */
package signChain;

import java.util.regex.Pattern;

/**
 * @author MuraoMakoto
 *
 * ���|�i�j �� ���i�jN
 *
 * �^�|�i�j �� �^�i�jN
 *
 * ���|�i�i�j�i�i�j�i�j�j�j �� ���i�i�j�i�i�j�i�j�j�jN
 *
 * �^�|�i�i�j�i�i�j�i�j�j�j �� �^�i�i�j�i�i�j�i�j�j�jN

 */
public class NegativeBetweenDivideORMultiAndOpeningParenthesis extends Support{

	/**
	 *
	 */
	public NegativeBetweenDivideORMultiAndOpeningParenthesis(String staffName) {
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		super(staffName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

		// *-(  �܂��� /-( �ɑΏ�����B
//		String trouble ="5*-(4+6)+5/-(4+(6+5)*5*(448.3349/(4344*(553+323))))";
//		StringBuilder sb = new StringBuilder(trouble);
//		while(Pattern.matches("(.*)([*/]{1})([-]{1})(\\({1})(.*)", sb)){
//			//System.out.println("matched");
//			int startPosition = 0;
//
//			for(int i=0;i<sb.length()-2;i++){
//				//System.out.println(sb.charAt(i)+","+sb.charAt(i+1)+","+sb.charAt(i+2));
//				if((sb.charAt(i)=='*' || sb.charAt(i)=='/') && sb.charAt(i+1)=='-' && sb.charAt(i+2)=='('){
//					startPosition = i+1;
//					break;
//				}
//			}
//
//
//			for(int i=startPosition+1,count = 0;i<sb.length();i++){
//				if(sb.charAt(i)=='('){
//					count++;
//				}else if(sb.charAt(i)==')'){
//					count--;
//				}
//
//				if(count==0){
//					sb.insert(i+1, 'N');
//					break;
//				}
//			}
//
//			sb.deleteCharAt(startPosition);
//		}
//
//
//		System.out.println(sb);

	}

	@Override
	public TroubleCode canIResolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		if(Pattern.matches("(.*)([*/]{1})([-]{1})(\\({1})(.*)", troubleCode.getTroubleMathCode())){
			return this.resolve(troubleCode);
		}else{
			return troubleCode;
		}
	}

	@Override
	public TroubleCode resolve(TroubleCode troubleCode) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		StringBuilder sb = new StringBuilder(troubleCode.getTroubleMathCode());
		while(Pattern.matches("(.*)([*/]{1})([-]{1})(\\({1})(.*)", sb)){
			int startPosition = 0;

			for(int i=0;i<sb.length()-2;i++){
				//System.out.println(sb.charAt(i)+","+sb.charAt(i+1)+","+sb.charAt(i+2));
				if((sb.charAt(i)=='*' || sb.charAt(i)=='/') && sb.charAt(i+1)=='-' && sb.charAt(i+2)=='('){
					startPosition = i+1;
					break;
				}
			}


			for(int i=startPosition+1,count = 0;i<sb.length();i++){
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

			sb.deleteCharAt(startPosition);
		}

		this.tc = new TroubleCode(sb.toString());
		this.review();
		return tc;
	}

}
