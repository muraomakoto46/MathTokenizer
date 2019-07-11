package signChain;

import java.util.regex.Pattern;

/**
 * @author MuraoMakoto
 *  42.....195 ⇒ 42.195
 * */
public class PointSurplus extends Support{

	public PointSurplus(String staffName) {
		super(staffName);
		// TODO 自動生成されたコンストラクター・スタブ
		this.si=new StrategicCompiler("(.*)([0-9]+)([.]{2,})([0-9]+)([N]{0,1})(.*)","$1$2.$4$5$6");
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		String query ="42.195";
		String pattern ="(.*)([0-9]+)([.]{2,})([0-9]+)([N]{0,1})(.*)";
		String catalyst="$1$2.$4$5$6";
		
		if(query.matches(pattern)){
			System.out.println("一致した");
		}else{
			System.out.println("一致しなかった。");
		}
		
		while(Pattern.matches(pattern, query)){
			query=query.replaceFirst(pattern, catalyst);
		}
		System.out.println("result="+ query);
	}

}
