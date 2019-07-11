/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 * this.si=new StrategicCompiler("(.*)([0-9.]+)([/*^]{1})(-{1})([0-9.]+)(.*)","$1$2$3$5N$6");
 * だと、
 * 3N*-3.0 に一致しない。
 */
public class MultiDivBeforeNegativeNumber extends Support{

	public MultiDivBeforeNegativeNumber(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
		this.si=new StrategicCompiler("(.*)([0-9.]+)([N]{0,1})([/*^]{1})(-{1})([0-9.]+)(.*)","$1$2$3$4$6N$7");
	}

	public static void main(String[] args){
		String query ="3N*-3.0";
		String pattern ="(.*)([0-9.]+)([/*^]{1})(-{1})([0-9.]+)(.*)";
		if(query.matches(pattern)){
			System.out.println("一致した");
		}else{
			System.out.println("一致しなかった。");
		}
	}
}
