/**
 *
 */
package signChain;

/**
 * @author makoto
 * -((1/3)-3)
 * =-((0.33)-3)
 * =-(0.33-3)
 * =-(0.33+3N)
 * =-(2.66N)
 * ==2.66NN
 * よって
 * 2.66NN
 * が発生する。Nは最大でも1連続しか現れない。
 */
public class NNNMultipleOccurrence extends Support{

	public NNNMultipleOccurrence(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
		this.si=new StrategicCompiler("(.*)([0-9.]+)([N]{2})(.*)", "$1$2$4");
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String trouble = "2.66NNNNNNNNNNNN+234";
		Support nnn = new NNNMultipleOccurrence("anzof");
		TroubleCode tc=new TroubleCode(trouble);
		tc=nnn.resolve(tc);

	}
}
