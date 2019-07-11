/**
 *
 */
package signChain;

/**
 * @author MuraoMakoto
 *
 */
public interface BeltConveyer {
	public abstract TroubleCode support(TroubleCode troubleCode);
	public abstract String toString();
	public abstract BeltConveyer setNext(BeltConveyer next);
	public abstract TroubleCode canIResolve(TroubleCode troubleCode);

	//canIResolve()�̒��Ŏg����B
	public abstract TroubleCode resolve(TroubleCode troubleCode);

	public abstract void review();
}
