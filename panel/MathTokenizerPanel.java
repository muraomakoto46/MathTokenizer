package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import sandBox.ParenthesisEncoder;

/**
 * @author MuraoMakoto
 * @version 7
 * @see
 * �������Ɍv�Z������͂���Enter�������ƁA�v�Z���ʂ��o��B
 * �܂��A���̌v�Z���ʂ����Ƃ� ��������������g�ݗ��ĂČv�Z���Ă������Ƃ��ł���B
 * MR �ɂ���
 * MR�͐����o�[�̕�����ƃ�������̕�����������������̂�V���������o�[�̕�����ɂ���B
 * MR�������O�ɁA
 * �����o�[�ŁA 3.14 �̂悤�Ȑ��l�̌��ɁA +�Ȃǂ̉��Z�q����͂��Ă����Ƃ����B
 *
 * F3�L�[�������ƁA�����o�[�̕������ 0�����̕����� "" �ɒu��������B
 * */
public class MathTokenizerPanel extends JFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = 6L;
	private Preferences preferences;
	private Container container;
	private JPanel upperPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel northWestButtonVerrey;
	private JPanel northEastTextPlain;
	private JButton MCButton;
	private JButton MRButton;
	private JButton MSButton;
	private JButton MRootButton;
	private JButton MPlusButton;
	private JButton MMinusButton;
	private JButton MMultiplyButton;
	private JButton MDivideButton;
	private JButton copyButton;
	private JLabel  MemoryIncluding;
	private JLabel inducer;
	private JTextField textField;
	private JTextField queryCodeField;


	public static void main(String[] args){
		new MathTokenizerPanel();
	}

	public MathTokenizerPanel(){
		preferences = Preferences.userRoot().node("MuraoMakoto/DivideAndConquerCalculatorV6");
		this.setTitle("�ł񂽂��� 2017�N12��18�� ������");

		//��ɐ錾���Ă����Ȃ��ƁA�Q�Ɛ悪�Ȃ��B
		this.container 		= this.getContentPane();
		this.upperPanel 		= new JPanel();
		this.middlePanel	= new JPanel();
		this.lowerPanel 		= new JPanel();
		this.northWestButtonVerrey = new JPanel();
		this.northEastTextPlain = new JPanel();

		this.MCButton        = new JButton("MC");
		this.MRButton        = new JButton("MR");
		this.MSButton        = new JButton("MS");
		this.MRootButton     = new JButton("��");
		this.MPlusButton     = new JButton("M+");
		this.MMinusButton    = new JButton("M-");
		this.MMultiplyButton = new JButton("M*");
		this.MDivideButton   = new JButton("M/");
		this.MemoryIncluding = new JLabel();
		this.inducer         = new JLabel();
		this.textField       = new JTextField();
		this.queryCodeField  = new JTextField("Calculator");
		this.copyButton = new JButton();




		//���C�A�E�g �z��
		upperPanel.setLayout(new GridLayout(1,2));
		middlePanel.setLayout(new BorderLayout());
		lowerPanel.setLayout(new BorderLayout());

		container.setLayout(new GridLayout(3,1));
		northWestButtonVerrey.setLayout(new GridLayout(2,4));


		northEastTextPlain.add(MemoryIncluding);

		northWestButtonVerrey.add(MCButton, 0);
		northWestButtonVerrey.add(MRButton, 1);
		northWestButtonVerrey.add(MSButton, 2);
		northWestButtonVerrey.add(MRootButton, 3);
		northWestButtonVerrey.add(MPlusButton, 4);
		northWestButtonVerrey.add(MMinusButton, 5);
		northWestButtonVerrey.add(MMultiplyButton, 6);
		northWestButtonVerrey.add(MDivideButton, 7);


		upperPanel.add(northWestButtonVerrey, 0);
		upperPanel.add(northEastTextPlain,1);


		this.middlePanel.add(queryCodeField);

		inducer.setText("����:");
		copyButton.setText("����");

		lowerPanel.add(inducer,BorderLayout.WEST);
		lowerPanel.add(textField,BorderLayout.CENTER);
		lowerPanel.add(copyButton,BorderLayout.EAST);

		Dimension dimension=new Dimension(472,18);
		upperPanel.setPreferredSize(dimension);
		middlePanel.setPreferredSize(dimension);
		lowerPanel.setPreferredSize(dimension);

		container.add(upperPanel, 0);
		container.add(middlePanel,1);
		container.add(lowerPanel,2);

		//JTextField#setToolTipText()�̈����ɕ������ݒ肷��ƁA
		//�I�u�W�F�N�g�̏�Ƀ}�E�X�J�[�\���������Ă���ƁA���̈����̕����񂪕\�������B
		textField.setToolTipText("�����ɐ�������͂��ĂˁB");
		this.queryCodeField.setToolTipText("���L����W���I�I����������");
		this.copyButton.setToolTipText("�������N���b�v�{�[�h�ɃR�s�[����B");


		MCButton.setToolTipText("MemoryClear");
		MRButton.setToolTipText("MemoryRecall: �����o�[ = Memory");
		MSButton.setToolTipText("MemorySet: Memory = �����o�[�̉��Z����");
		MRootButton.setToolTipText("SquareRoot:Memory = Memory ^ (1/2)");
		MPlusButton.setToolTipText("Memory=Memory�̒l + �����o�[�̉��Z����");
		MMinusButton.setToolTipText("Memory=Memory�̒l - �����o�[�̉��Z����");
		MMultiplyButton.setToolTipText("Memory=Memory�̒l * �����o�[�̉��Z����");
		MDivideButton.setToolTipText("Memory=Memory �� �����o�[�̉��Z����");

		this.MemoryIncluding.setToolTipText("�l�������Ă��܂��B");
		this.northEastTextPlain.setToolTipText("Memory�̓��e");

		textField.setName("Numerical expression");


		this.queryCodeField.setOpaque(true);

		//����A�C�R���ݒ�
		Image imageIcon = Toolkit.getDefaultToolkit().getImage("./bin/png/solarPNG.png");
//		File testImage = new File("./bin/png/solarPNG.png");
//		System.out.println(testImage.exists());
		this.setIconImage(imageIcon);

		// Font �� �{�[�_�[�ݒ�
		TitledBorder border =new TitledBorder("Memory");
		TitledBorder MathEriaBorder = new TitledBorder("�����o�[");
		Font borderFont =new Font(Font.SANS_SERIF,Font.ITALIC,16);
		Font buttonFont = new Font(Font.DIALOG_INPUT,Font.PLAIN,20);
		Font fontOfMemory = new Font(Font.DIALOG_INPUT,Font.BOLD,18);
		Font fontOfTextField = new Font(Font.DIALOG_INPUT,Font.TRUETYPE_FONT,18);
		Font fontOfQueryTextField = new Font(Font.DIALOG_INPUT,Font.TRUETYPE_FONT,21);



		border.setTitleColor(new Color(0,0,240));
		border.setTitleFont(borderFont);
		border.setTitlePosition(TitledBorder.CENTER);
		border.setTitleJustification(TitledBorder.DEFAULT_JUSTIFICATION);

		this.MemoryIncluding.setSize(140, 60);
		this.MemoryIncluding.setVerticalAlignment(0);
		this.MemoryIncluding.setHorizontalAlignment(0);


		this.northEastTextPlain.setFont(fontOfMemory);
		this.MemoryIncluding.setFont(fontOfMemory);
		this.northEastTextPlain.setBorder(border);
		this.textField.setFont(fontOfTextField);
		this.queryCodeField.setFont(fontOfQueryTextField);
		this.lowerPanel.setBorder(MathEriaBorder);


		this.MCButton.setFont(buttonFont);
		this.MSButton.setFont(buttonFont);
		this.MRButton.setFont(buttonFont);
		this.MPlusButton.setFont(buttonFont);
		this.MMinusButton.setFont(buttonFont);
		this.MMultiplyButton.setFont(buttonFont);
		this.MDivideButton.setFont(buttonFont);
		this.MRootButton.setFont(buttonFont);

		this.copyButton.setBackground(new Color(255,184,48));
		this.lowerPanel.setBackground(new Color(153,111,0));
		this.textField.setBackground(new Color(188,186,173));
		this.queryCodeField.setBackground(new Color(0,0,0));
		this.northEastTextPlain.setBackground(new Color(110,141,188));
		this.queryCodeField.setForeground(new Color(220,210,200));

		//�F�ݒ�
		Color memoryButtonColor = new Color(230,230,0);
		Color memoryCalculationButtonColor = new Color(223,247,159);
		this.MCButton.setBackground(memoryButtonColor);
		this.MRButton.setBackground(memoryButtonColor);
		this.MSButton.setBackground(memoryButtonColor);
		this.MRootButton.setBackground(memoryCalculationButtonColor);
		this.MPlusButton.setBackground(memoryCalculationButtonColor);
		this.MMinusButton.setBackground(memoryCalculationButtonColor);
		this.MMultiplyButton.setBackground(memoryCalculationButtonColor);
		this.MDivideButton.setBackground(memoryCalculationButtonColor);
		//���̃E�B���h�E����Ɏ�O�ɕ\������B
		this.setAlwaysOnTop(true);


		//�A�N�V�����o�^
		copyButton.addMouseListener(new ClipboardCopyEvent(textField));
		MCButton.addMouseListener(new MCButtonMouseEvent(MemoryIncluding));
		MRButton.addMouseListener(new MRButtonMouseEvent(MemoryIncluding,textField));
		MSButton.addMouseListener(new MSButtonMouseEvent(MemoryIncluding,textField));
		MPlusButton.addMouseListener(new MPlusButtonMouseEvent(MemoryIncluding,textField));
		MMinusButton.addMouseListener(new MMinusButtonMouseEvent(MemoryIncluding,textField));
		MMultiplyButton.addMouseListener(new MMultiplyButtonMouseEvent(MemoryIncluding,textField));
		MDivideButton.addMouseListener(new MDivideButtonMouseEvent(MemoryIncluding,textField));
		MRootButton.addMouseListener(new MRootButtonMouseEvent(MemoryIncluding));
		textField.addKeyListener(new EnterKeyEvent(textField,this.queryCodeField) );
		this.queryCodeField.setDragEnabled(true);
		this.textField.setDragEnabled(true);
		//this.addKeyListener(new FunctionKeyEvent(textField)); // ���ʂ��Ȃ������B
		//this.getContentPane().addKeyListener(new FunctionKeyEvent(textField)); //��������ʂ��Ȃ������B
		this.setVisible(true);
		this.addWindowListener(new WindowSizeRemember(preferences));
	}

	//�N���b�v�{�[�h�փR�s�[����B
	private final class ClipboardCopyEvent extends MouseAdapter{
		private JTextField textField;
		private Toolkit toolkit = Toolkit.getDefaultToolkit();
		private Clipboard clip = toolkit.getSystemClipboard();
		public ClipboardCopyEvent(JTextField textField){
			this.textField = textField;
		}
		@Override
		public void mouseClicked(MouseEvent e){
			System.out.println("Clipboard copy");
			StringSelection selection = new StringSelection(textField.getText());
			clip.setContents(selection, selection);
		}
	}


	//MC �{�^�� �E��̃e�L�X�g�G���A�̕������""�ɂ���B
	private final class MCButtonMouseEvent extends MouseAdapter{
		JLabel code;
		public MCButtonMouseEvent(JLabel label){
			this.code=label;
		}
		@Override
		public void mouseClicked(MouseEvent e){
			code.setText("");
		}
	}

	private final class MRButtonMouseEvent extends MouseAdapter{
		JLabel code;
		JTextField textField;
		public MRButtonMouseEvent(JLabel label,JTextField textField){
			this.code=label;
			this.textField=textField;
		}
		@Override
		public void mouseClicked(MouseEvent e){
			StringBuilder sb=new StringBuilder();
			sb.append(this.textField.getText());
			sb.append(this.code.getText());
			this.textField.setText(sb.toString());
		}
	}

	private final class MSButtonMouseEvent extends MouseAdapter{
		private JLabel code;
		private JTextField textField;
		public MSButtonMouseEvent(JLabel label,JTextField textField){
			this.code=label;
			this.textField=textField;
		}
		@Override
		public void mouseClicked(MouseEvent e){
			ParenthesisEncoder pe = new ParenthesisEncoder(this.textField.getText());
			this.code.setText(pe.encodeMathCode());
		}
	}

	//M+ �������ꂽ�Ƃ�
	private final class MPlusButtonMouseEvent extends MouseAdapter{
		private JTextField textField;
		private JLabel label;

		public MPlusButtonMouseEvent(JLabel label,JTextField textField){
			this.textField = textField;
			this.label=label;
		}

		@Override
		public void mouseClicked(MouseEvent e){
			String mainResult=new ParenthesisEncoder(textField.getText()).encodeMathCode();

			if(label.getText().length()>0){
				mainResult = new ParenthesisEncoder(label.getText()+"+"+mainResult).encodeMathCode();
			}
			this.label.setText(mainResult);
		}
	}

	//M-�������ꂽ�Ƃ�
	private final class MMinusButtonMouseEvent extends MouseAdapter{
		private JTextField textField;
		private JLabel label;

		public MMinusButtonMouseEvent(JLabel label,JTextField textField){
			this.textField = textField;
			this.label=label;
		}

		@Override
		public void mouseClicked(MouseEvent e){
			String mainResult=new ParenthesisEncoder(textField.getText()).encodeMathCode();

			if(label.getText().length()>0){
				mainResult = new ParenthesisEncoder(label.getText()+"-"+mainResult).encodeMathCode();
			}
			this.label.setText(mainResult);
		}
	}

	//M*�������ꂽ�Ƃ�

	private final class MMultiplyButtonMouseEvent extends MouseAdapter{
		private JTextField textField;
		private JLabel label;

		public MMultiplyButtonMouseEvent(JLabel label,JTextField textField){
			this.textField = textField;
			this.label=label;
		}

		@Override
		public void mouseClicked(MouseEvent e){
			String mainResult=new ParenthesisEncoder(textField.getText()).encodeMathCode();

			if(label.getText().length()>0){
				mainResult = new ParenthesisEncoder(label.getText()+"*"+mainResult).encodeMathCode();
			}
			this.label.setText(mainResult);
		}
	}

	//M/�������ꂽ�Ƃ�
	private final class MDivideButtonMouseEvent extends MouseAdapter{
		private JTextField textField;
		private JLabel label;

		public MDivideButtonMouseEvent(JLabel label,JTextField textField){
			this.textField = textField;
			this.label=label;
		}

		@Override
		public void mouseClicked(MouseEvent e){
			String mainResult=new ParenthesisEncoder(textField.getText()).encodeMathCode();

			if(label.getText().length()>0){
				mainResult = new ParenthesisEncoder(label.getText()+"/"+mainResult).encodeMathCode();
			}
			this.label.setText(mainResult);
		}
	}

	// ��M�������ꂽ�Ƃ�
	private final class MRootButtonMouseEvent extends MouseAdapter{
		private JLabel label;
		private double data=0;

		public MRootButtonMouseEvent(JLabel label){
			this.label=label;
		}

		@Override
		public void mouseClicked(MouseEvent e){

			if(label.getText().length()>0){
				this.data = Double.valueOf(label.getText());

			}else{
				this.data=0;
			}

			if(this.data>0){
				this.data=Math.sqrt(this.data);
			}

			this.label.setText(String.valueOf(data));
		}
	}

	private final class EnterKeyEvent extends KeyAdapter{
		private ParenthesisEncoder pe;
		private JTextField textField;
		private JTextField queryTextField;
		private String query;
		public EnterKeyEvent(JTextField textField,JTextField queryTextField){
			this.textField = textField;
			this.queryTextField=queryTextField;
		}

		@Override
		public void keyPressed(KeyEvent e){
			//System.out.println("e.getKeyCode() = "+e.getKeyCode() +"; e.getKeyLocation() = "+ e.getKeyLocation());
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				//System.out.println("Enter�ƈ�v�����B");
				this.query = this.textField.getText();
				this.queryTextField.setText(this.query);
				this.pe = new ParenthesisEncoder(this.query);
				this.textField.setText(this.pe.encodeMathCode());
				this.queryTextField.setBackground(new Color(159,216,214));
				queryCodeField.setForeground(new Color(20,10,0));
				this.queryTextField.setToolTipText("���Ȃ������O�ɓ��͂�������");
			}
			else if(e.getKeyCode() == KeyEvent.VK_F3){
				//System.out.println("F3�������ꂽ�BtextField�̕������0�����̕�����ɕϊ����܂��B");
				this.textField.setText("");
				this.query="";
			}
			else if(e.getKeyCode() == KeyEvent.VK_F6){
				this.textField.setText(this.textField.getText() + String.valueOf(Math.PI));
			}
			// VK_7 �� VK_F7���������悤�B
			else if(e.getKeyCode() == KeyEvent.VK_F7){
				this.query=this.textField.getText();
				this.query=this.query+String.valueOf(Math.E);
				this.textField.setText(this.query);
//				System.out.println("E=" + query);
			}
			else if(e.getKeyCode() == KeyEvent.VK_F8){
				//System.out.println("F8�������ꂽ�B�ō��v�Z");
				this.query=this.textField.getText();
				this.textField.setText(this.query + "*1.08");
			}
			//JFrame�ɂƂ���F10�͓��ʂȃL�[�炵���BF10�ɉ����@�\�����t���悤�Ƃ���Ƃ��܂������Ȃ��B
			else if(e.getKeyCode() == KeyEvent.VK_F11){
				//System.out.println("F10�������ꂽ�B�ō��v�Z");
				//��xBackSpace�������Ă���łȂ��ƁAF3�őS�����ł��Ȃ��B
				this.query=this.textField.getText();
				this.query=this.query + "*1.1";
				this.textField.setText(this.query);
			}
			// log e(arg0) �����߂�B textField�̒l��e�̉���ɑ�������̂������߂�B
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_E){
				System.out.println("CTRL�{E�������ꂽ");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.log(value));
			}
			// log 10(arg0) �����߂�B
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_L){
				System.out.println("CTRL�{L�������ꂽ Math.log10()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.log10(value));
			}

			// Ctrl+R �� ���[�g���Z
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_R){
				System.out.println("CTRL�{R�������ꂽ Math.sqrt()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.sqrt(value));
			}

			// sin(�����o�[�̒l) �� ���߂�B�������A�����o�[�̒l�͊p�x�ł���A���A�P�ʂ̓΃��W�A���łȂ���΂Ȃ�Ȃ��B
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S){
				System.out.println("CTRL�{S�������ꂽ Math.sin()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.sin(value));
			}
			// cos(�����o�[�̒l) �� ���߂�B�������A�����o�[�̒l�͊p�x�ł���A���A�P�ʂ̓΃��W�A���łȂ���΂Ȃ�Ȃ��B
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_C){
				System.out.println("CTRL�{C�������ꂽ Math.cos()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.cos(value));
			}
			// tan(�����o�[�̒l) �� ���߂�B�������A�����o�[�̒l�͊p�x�ł���A���A�P�ʂ̓΃��W�A���łȂ���΂Ȃ�Ȃ��B
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_T){
				System.out.println("CTRL�{T�������ꂽ Math.tan()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.tan(value));
			}
			// asin(�����o�[�̒l) �� ���߂�B������sin(��)�ŋ��߂���悤�Ȓl�B�߂�l�͊p�x�ł���A�P�ʂ̓΃��W�A���ł���B
			// asin(sin��) = �� �Ƃ����֌W������B
			else if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_S){
				System.out.println("ALT�{C�������ꂽ Math.asin()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.asin(value));

			}
			else if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_C){
				System.out.println("ALT�{C�������ꂽ Math.acos()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.acos(value));
			}
			else if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_T){
				System.out.println("ALT�{C�������ꂽ Math.atan()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.atan(value));
			}

			// hyperbolic sin
			else if(e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_S){
				System.out.println("Shift�{C�������ꂽ Math.asin()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.sinh(value));

			}
			else if(e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_C){
				System.out.println("Shift�{C�������ꂽ Math.acos()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.cosh(value));
			}

			else if(e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_T){
				System.out.println("Shift�{C�������ꂽ Math.atan()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.tanh(value));
			}

			// �l�̌ܓ�
			else if(e.getKeyCode()==KeyEvent.VK_F9){
				System.out.println("F9�������ꂽ round");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.round(value));
			}


			else{
				;
			}
		}

		private void assistMathCalculation(double value){
			System.out.println("value = " + value);
			this.query=String.valueOf(value);
			this.pe=new ParenthesisEncoder(this.query);
			this.query=pe.encodeMathCode();
			this.textField.setText(this.query);
		}
	}
}
