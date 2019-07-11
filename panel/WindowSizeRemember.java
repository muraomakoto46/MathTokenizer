package panel;

import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;
/**Preferences API��p���āA�E�B���h�E�̃T�C�Y�ƈʒu���o��������B
 * @author Murao Makoto 2014/3/2 Sunday
 * USAGE �g�p���@
 * import java.util.prefs.Preferences;
 * public class Recurrence extends JFrame{
 * 		Preferences preferences;
 * 		public Recurrence(){
 * 			preferences = Preferences.userRoot().node("makoto/DivideAndConquerCalculatorV3");
*			this.setTitle("������������ �����񐔎��l���v�Z");
*
*			this.setVisible(true);
*			this.addWindowListener(new WindowSizeRemember(preferences));
*		}
*}
* */
public class WindowSizeRemember extends WindowAdapter{
	private Preferences preferences;
	/* private WindowShape ws = new WindowShape("abcde.xml");
	 * ���X�i�N���X�� ��O�𓊂���ꍇ��������̂͋L�q�ł��܂���B
	 * */
	private Rectangle rectangle;

	public WindowSizeRemember(Preferences preferences){
		this.preferences=preferences;
	}

	@Override
	public void windowOpened(WindowEvent e){
		e.getWindow().setBounds(preferences.getInt("x", 100),preferences.getInt("y", 100),preferences.getInt("width", 482),preferences.getInt("height", 150));
	}

	@Override
	public void windowClosing(WindowEvent e){
		this.rectangle=e.getWindow().getBounds();//���B�厖�Ȃ��͉̂��ł�e�������Ă��I
		//�ł�WindowShape�͎����ĂȂ�����
		//����ς�extends����setWindowShape()���\�b�h��ǉ�����K�v����ˁB
		preferences.putInt("x", rectangle.x);
		preferences.putInt("y", rectangle.y);
		preferences.putInt("width", rectangle.width);
		preferences.putInt("height", rectangle.height);
		System.exit(0);
	}

	@Override
	public void windowGainedFocus(WindowEvent e){
		//System.out.println("window Gained Focus");
	}

	@Override
	public void windowLostFocus(WindowEvent e){
		//System.out.println("window LOST Focus");
	}
	@Override
	public void windowActivated(WindowEvent e){
		//System.out.println("Window Activated");

	}

	@Override
	public void windowDeactivated(WindowEvent e){
		//System.out.println("Window De-activated");
	}
}