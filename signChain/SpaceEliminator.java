/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 * 文字列から空白を除去する。
 */
public class SpaceEliminator extends Support{
	public SpaceEliminator(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
		this.si=new StrategicCompiler("^(.*)(\\p{Space}+)(.*)$","$1$3");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
//		String trouble = "12    -  334 +      556";

//		if(Pattern.matches("(.*)(\\p{Space})(.*)", trouble)){
//			System.out.println("マッチした");
//		}else{
//			System.out.println("マッチしなかった。");
//		}

//		SpaceEliminator su=new SpaceEliminator("space man");
//		TroubleCode query = new TroubleCode(trouble);
//		TroubleCode gain = su.canIResolve(query);
//		System.out.println("gain = " + gain.getTroubleMathCode());
	}

}
