/**
 *
 */
package signChain;

/**
 * @author makoto
 * ここでは -ではじまり、直後に 数字が来る場合のみをサポートする。
 * -8  ⇒ 8N
 * -9+4 ⇒ 9N+4
 * -3342 ⇒ 3342N
 * -42.195 ⇒ 42.195N

 */
public class LeftEdgeNegativeSupport extends Support{

	public LeftEdgeNegativeSupport(String staffName){
		super(staffName);
		this.si = new StrategicCompiler("^(-{1})([0-9.]+)(.*)$","$2N$3");
	}
}
