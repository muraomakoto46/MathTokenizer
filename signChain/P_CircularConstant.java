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
		// TODO 自動生成されたコンストラクター・スタブ
		this.si=new StrategicCompiler("^(.*)([P]{1})(.*)$","$13.1415926535897932384626433832795$3");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String query ="123*e*456";
		String pattern = "^(.*)([e]{1})(.*)$";
		if(query.matches(pattern)){
			System.out.println("match");
		}else{
			System.out.println("一致しなかった。");
		}

		while(query.matches(pattern)){
			query = query.replaceFirst(pattern,"$13.1415926535897932384626433832795$3");
		}

		System.out.println(query);
	}

}
