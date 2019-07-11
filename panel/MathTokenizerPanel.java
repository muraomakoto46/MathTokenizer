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
 * 数式欄に計算式を入力してEnterを押すと、計算結果が出る。
 * また、その計算結果をもとに 引き続き数式を組み立てて計算していくことができる。
 * MR について
 * MRは数式バーの文字列とメモリ上の文字列を結合したものを新しい数式バーの文字列にする。
 * MRを押す前に、
 * 数式バーで、 3.14 のような数値の後ろに、 +などの演算子を入力しておくといい。
 *
 * F3キーを押すと、数式バーの文字列を 0文字の文字列 "" に置き換える。
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
		this.setTitle("でんたくん 2017年12月18日 村尾誠");

		//先に宣言しておかないと、参照先がない。
		this.container 		= this.getContentPane();
		this.upperPanel 		= new JPanel();
		this.middlePanel	= new JPanel();
		this.lowerPanel 		= new JPanel();
		this.northWestButtonVerrey = new JPanel();
		this.northEastTextPlain = new JPanel();

		this.MCButton        = new JButton("MC");
		this.MRButton        = new JButton("MR");
		this.MSButton        = new JButton("MS");
		this.MRootButton     = new JButton("√");
		this.MPlusButton     = new JButton("M+");
		this.MMinusButton    = new JButton("M-");
		this.MMultiplyButton = new JButton("M*");
		this.MDivideButton   = new JButton("M/");
		this.MemoryIncluding = new JLabel();
		this.inducer         = new JLabel();
		this.textField       = new JTextField();
		this.queryCodeField  = new JTextField("Calculator");
		this.copyButton = new JButton();




		//レイアウト 配備
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

		inducer.setText("数式:");
		copyButton.setText("複製");

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

		//JTextField#setToolTipText()の引数に文字列を設定すると、
		//オブジェクトの上にマウスカーソルを持ってくると、その引数の文字列が表示される。
		textField.setToolTipText("ここに数式を入力してね。");
		this.queryCodeField.setToolTipText("☆広告募集中！！☆☆☆☆☆");
		this.copyButton.setToolTipText("数式をクリップボードにコピーする。");


		MCButton.setToolTipText("MemoryClear");
		MRButton.setToolTipText("MemoryRecall: 数式バー = Memory");
		MSButton.setToolTipText("MemorySet: Memory = 数式バーの演算結果");
		MRootButton.setToolTipText("SquareRoot:Memory = Memory ^ (1/2)");
		MPlusButton.setToolTipText("Memory=Memoryの値 + 数式バーの演算結果");
		MMinusButton.setToolTipText("Memory=Memoryの値 - 数式バーの演算結果");
		MMultiplyButton.setToolTipText("Memory=Memoryの値 * 数式バーの演算結果");
		MDivideButton.setToolTipText("Memory=Memory ÷ 数式バーの演算結果");

		this.MemoryIncluding.setToolTipText("値が入っています。");
		this.northEastTextPlain.setToolTipText("Memoryの内容");

		textField.setName("Numerical expression");


		this.queryCodeField.setOpaque(true);

		//左上アイコン設定
		Image imageIcon = Toolkit.getDefaultToolkit().getImage("./bin/png/solarPNG.png");
//		File testImage = new File("./bin/png/solarPNG.png");
//		System.out.println(testImage.exists());
		this.setIconImage(imageIcon);

		// Font と ボーダー設定
		TitledBorder border =new TitledBorder("Memory");
		TitledBorder MathEriaBorder = new TitledBorder("数式バー");
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

		//色設定
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
		//このウィンドウを常に手前に表示する。
		this.setAlwaysOnTop(true);


		//アクション登録
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
		//this.addKeyListener(new FunctionKeyEvent(textField)); // 効果がなかった。
		//this.getContentPane().addKeyListener(new FunctionKeyEvent(textField)); //これも効果がなかった。
		this.setVisible(true);
		this.addWindowListener(new WindowSizeRemember(preferences));
	}

	//クリップボードへコピーする。
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


	//MC ボタン 右上のテキストエリアの文字列を""にする。
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

	//M+ が押されたとき
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

	//M-が押されたとき
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

	//M*が押されたとき

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

	//M/が押されたとき
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

	// √Mが押されたとき
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
				//System.out.println("Enterと一致した。");
				this.query = this.textField.getText();
				this.queryTextField.setText(this.query);
				this.pe = new ParenthesisEncoder(this.query);
				this.textField.setText(this.pe.encodeMathCode());
				this.queryTextField.setBackground(new Color(159,216,214));
				queryCodeField.setForeground(new Color(20,10,0));
				this.queryTextField.setToolTipText("あなたが直前に入力した数式");
			}
			else if(e.getKeyCode() == KeyEvent.VK_F3){
				//System.out.println("F3が押された。textFieldの文字列を0文字の文字列に変換します。");
				this.textField.setText("");
				this.query="";
			}
			else if(e.getKeyCode() == KeyEvent.VK_F6){
				this.textField.setText(this.textField.getText() + String.valueOf(Math.PI));
			}
			// VK_7 と VK_F7を見分けよう。
			else if(e.getKeyCode() == KeyEvent.VK_F7){
				this.query=this.textField.getText();
				this.query=this.query+String.valueOf(Math.E);
				this.textField.setText(this.query);
//				System.out.println("E=" + query);
			}
			else if(e.getKeyCode() == KeyEvent.VK_F8){
				//System.out.println("F8が押された。税込計算");
				this.query=this.textField.getText();
				this.textField.setText(this.query + "*1.08");
			}
			//JFrameにとってF10は特別なキーらしい。F10に何か機能を取り付けようとするとうまくいかない。
			else if(e.getKeyCode() == KeyEvent.VK_F11){
				//System.out.println("F10が押された。税込計算");
				//一度BackSpaceを押してからでないと、F3で全消去できない。
				this.query=this.textField.getText();
				this.query=this.query + "*1.1";
				this.textField.setText(this.query);
			}
			// log e(arg0) を求める。 textFieldの値がeの何乗に相当するのかを求める。
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_E){
				System.out.println("CTRL＋Eが押された");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.log(value));
			}
			// log 10(arg0) を求める。
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_L){
				System.out.println("CTRL＋Lが押された Math.log10()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.log10(value));
			}

			// Ctrl+R で ルート演算
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_R){
				System.out.println("CTRL＋Rが押された Math.sqrt()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.sqrt(value));
			}

			// sin(数式バーの値) を 求める。ただし、数式バーの値は角度であり、かつ、単位はπラジアンでなければならない。
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S){
				System.out.println("CTRL＋Sが押された Math.sin()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.sin(value));
			}
			// cos(数式バーの値) を 求める。ただし、数式バーの値は角度であり、かつ、単位はπラジアンでなければならない。
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_C){
				System.out.println("CTRL＋Cが押された Math.cos()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.cos(value));
			}
			// tan(数式バーの値) を 求める。ただし、数式バーの値は角度であり、かつ、単位はπラジアンでなければならない。
			else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_T){
				System.out.println("CTRL＋Tが押された Math.tan()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.tan(value));
			}
			// asin(数式バーの値) を 求める。引数はsin(θ)で求められるような値。戻り値は角度であり、単位はπラジアンである。
			// asin(sinθ) = θ という関係がある。
			else if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_S){
				System.out.println("ALT＋Cが押された Math.asin()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.asin(value));

			}
			else if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_C){
				System.out.println("ALT＋Cが押された Math.acos()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.acos(value));
			}
			else if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_T){
				System.out.println("ALT＋Cが押された Math.atan()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.atan(value));
			}

			// hyperbolic sin
			else if(e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_S){
				System.out.println("Shift＋Cが押された Math.asin()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.sinh(value));

			}
			else if(e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_C){
				System.out.println("Shift＋Cが押された Math.acos()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.cosh(value));
			}

			else if(e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_T){
				System.out.println("Shift＋Cが押された Math.atan()");
				this.query = this.textField.getText();
				double value = Double.valueOf(this.query);
				this.assistMathCalculation(Math.tanh(value));
			}

			// 四捨五入
			else if(e.getKeyCode()==KeyEvent.VK_F9){
				System.out.println("F9が押された round");
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
