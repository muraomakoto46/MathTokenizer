/**
 *
 */
package signChain;

/**
 * @author makoto
 * �����ł� -�ł͂��܂�A����� ����������ꍇ�݂̂��T�|�[�g����B
 * -8  �� 8N
 * -9+4 �� 9N+4
 * -3342 �� 3342N
 * -42.195 �� 42.195N

 */
public class LeftEdgeNegativeSupport extends Support{

	public LeftEdgeNegativeSupport(String staffName){
		super(staffName);
		this.si = new StrategicCompiler("^(-{1})([0-9.]+)(.*)$","$2N$3");
	}
}
