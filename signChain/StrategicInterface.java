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

	//canIResolve()�̒��Ŏg����B
	public abstract TroubleCode resolve(TroubleCode troubleCode);
}
