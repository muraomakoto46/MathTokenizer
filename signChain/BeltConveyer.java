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

	//canIResolve()の中で使われる。
	public abstract TroubleCode resolve(TroubleCode troubleCode);

	public abstract void review();
}
