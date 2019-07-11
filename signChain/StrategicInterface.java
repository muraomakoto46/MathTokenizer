/**
 *
 */
package signChain;

/**
 * @author makoto
 *
 */
public interface StrategicInterface {
	public abstract TroubleCode canIResolve(TroubleCode troubleCode);

	//canIResolve()の中で使われる。
	public abstract TroubleCode resolve(TroubleCode troubleCode);
}
