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

	//canIResolve()‚Ì’†‚Åg‚í‚ê‚éB
	public abstract TroubleCode resolve(TroubleCode troubleCode);
}
