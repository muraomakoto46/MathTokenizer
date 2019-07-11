/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 *
 * 事細かい 正規表現のパターンとリプレースパターンをいちいち設定しなくてもいいようになっている。
 * 事前に設定しておいて、その設定に名前が付けておくんだ。
 * そうすれば 正規表現を気にしないでこれを使うだけで サニタイジングができるようになるんだ。
 *
 */
public class NumNegNumChangeNumPlusNumNmark extends Support{

	public NumNegNumChangeNumPlusNumNmark(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
		this.si = new StrategicCompiler("(.*)([0-9.]+)([-]{1})([0-9.]+)(.*)", "$1$2\\+$4N$5");
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO 自動生成されたメソッド・スタブ
//
//		String trouble = "-((1/3)-3)";
//		if(trouble.matches("(.*)([0-9.]+)([-]{1})([0-9.]+)(.*)")){
//			System.out.println("match");
//		}else{
//			System.out.println("一致しなかった。");
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
