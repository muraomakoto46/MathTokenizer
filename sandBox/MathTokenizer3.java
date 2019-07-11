package sandBox;

import java.util.StringTokenizer;

/**
 * ������������ �����񐔎��l���v�Z
 * @author Murao Makoto 2014/03/2
 *
 * google�����o�[�ɁA�v�Z�������Č����{�^���������ƁA�������\�������B
 * ����Ƃ悭�����������s���Ă����B
 * google�̓��[�h���Ȃ���Όv�Z���ʂ��o�Ȃ����A���̃N���X��JTextField���g���΂����ɂł�B
 * ���̃N���X�ł� �����̎l���v�Z���T�|�[�g����B
 * () �� �g���܂���B
 * �ċA�I�ɋ��߂ď�ʂ�double�l��ԋp���悤�Ƃ���B
 * �ێ琫�Ɗg�������F�߂��Ȃ��\�[�X�R�[�h���B
 *USAGE �g�p�@:
 *String mathCode="987/65-4*3+2-1"
 *MathTokenizer mt = new MathTokenizer(mathCode);
 *Double result = mt.separete();
 *System.out.println("result = " + result);
 *
 *
 *
 *���̌v�Z���ł���B
 * 2+3
 * 8-4
 * 1+2+3+4+5+6+7+8+9+10 ���̏ꍇ�́A55���o�͂���邾�낤�B
 * 4+2*5 ���̏ꍇ�� 4+(2*5) ���Ƃ݂Ȃ����B
 * 2*6+9*7 ���̏ꍇ��(2*6)+(9*7) ���Ƃ݂Ȃ����B
 * 64*2+4 ���̏ꍇ�� (64*2)+4 ���Ƃ݂Ȃ����B
 * 42.195*3.14+2.71
 *
 * �G���[���͂��ꂽ�ꍇ�� �܂��܂�����Ȃ�ɑΏ����Ă����B
 * 9+-2 ���̏ꍇ�� 9-2 ���Ƃ݂Ȃ����B
 * 9-+2 ���̏ꍇ�� 9+2 ���Ƃ݂Ȃ����B
 * 5++++++++6 ���̏ꍇ�� 5+6���Ƃ݂Ȃ���āA11�ɂȂ�B
 * 2*3/4*5 ���̏ꍇ�� ((2*3)/4)*5 ���Ƃ݂Ȃ���āA 7.5�ɂȂ�B
 *
 * -4*8  ���̏ꍇ�́A(0-(4*8)) ���Ƃ݂Ȃ���āA-32�ɂȂ�B
 *
 *���L�̌v�Z�͂ł��Ȃ��B
 * -4*-5 ���̏ꍇ�́A0-4-5���Ƃ݂Ȃ����B
 * -18/-3 ���̏ꍇ�� 0-18-3 �Ƃ݂Ȃ����B
 * -4^2 ���̏ꍇ�́A= 0-(4^2) ���Ƃ݂Ȃ����B
 *
 * ���ʂ����p�ł��܂���B
 *  (19-7)*13
 **/
public class MathTokenizer3 {
	private String mathCode;
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String mathCode = "-4^2";
		MathTokenizer3 mt = new MathTokenizer3(mathCode);
		Double result = mt.separate();
		System.out.println("result = " + result);
	}

	public MathTokenizer3(String mathCode){
		this.mathCode=mathCode;
		if(mathCode.startsWith("-")){
			StringBuilder mending= new StringBuilder();
			mending.append("0");
			mending.append(mathCode);
			this.mathCode=mending.toString();
		}
		//mathCode���� ���O�ɋ󔒕����Ȃǂ��������Ă����K�v������B

		//mathCode��"" 0������������ 0����͂���B
		if(mathCode.length()==0){
			this.mathCode="0";
		}

	}
	public Double separate(){
		double result=0;
		/**
		 * ��n���ꂽmathCode�� �ǂ�� ���Z�q���܂܂�Ă��܂����B
		 * ���Z�q�ɉ����čł��K�؂Ȗ��߂������܂��傤�B
		 *
		 * */
		if(mathCode.isEmpty()){
			throw new IllegalArgumentException("�󕶎��� �����G���[");
		}

		// + �� - �ŕ�����ق��� * �� /�ŕ�������� �D�悵�Ȃ���΂Ȃ�Ȃ��B
		//��������if���� �Ăяo�������� �͂�����ƌ��܂��Ă���B

		// + �ŕ�����


		// ���K�\�����g���Ƃ�����i�����邯�ǁB C&R�������� Java�t�����n���h�u�b�N 286�y�[�W�Q��
		if(mathCode.indexOf("+")!=-1){
			//System.out.println("++++");
			result = separate(this.mathCode,"+");
			return result;
		}

		// - �ŕ�����
		if(mathCode.indexOf("-")!=-1){
			//System.out.println("----");
			result = separate(this.mathCode,"-");
			return result;
		}

		// * �ŕ�����
		if(mathCode.indexOf("*")!=-1){
			result = separate(this.mathCode,"*");
			return result;
		}
		// / �ŕ�����B
		if(mathCode.indexOf("/")!=-1){
			result = separate(this.mathCode,"/");
			return result;
		}

		// ^ �ŕ�����
		if(mathCode.indexOf("^")!=-1){
			result = separate(this.mathCode,"^");
			return result;
		}

		//������͍Ō�ɏ����Ȃ���΂Ȃ�Ȃ��B
		/* mathCode.matches("^\\d+$") ���ƁA�����_���ԂɊ܂܂�Ă�����͔̂������Ȃ���񂩁B*/
		if(mathCode.matches("^\\d+.{0,1}\\d*$")){
			//System.out.println("���l");
			return Double.parseDouble(mathCode);
		}


		return result;//�����ŕԂ���邱�Ƃ͂Ȃ��B
	}

	/**���̂������� 3�~3-3�� ���܂������ւ�
	 * 3+3*3 ���Ƃ��܂������B
	 * 3*3+3 OK
	 * 3+3+3 OK
	 * 4+3*3+4 OK
	 * 10-2-3 OK
	 * 4*5*6 OK
	 * 60/2/3 OK
	 * 50/2+15 OK
	 * 5+5-3 OK
	 * 2+3*4+567/8+9*10 OK
	 * 10-5-2 OK
	 * 5*5-3*3 NG -34�ɂȂ����B���܂������ĂȂ��B
	 * -7 NG +7�ɂȂ����B
	 * 8-5 �� 8 + (-5) ���� �݂Ȃ���Ɗy�Ȃ̂����B
	 * -4 + 5 = (-4) + 5
	 * -8 - 7 = (-8) + (-7)
	 * 2+3*4+567/8-9*10 =(2)+(3*4)+(567/8-9*10) = (2)+(3*4)+((567/8)+(-9*10))
	 * @throws Exception */
	private Double separate(String code,String tokenizer){
		StringTokenizer st = new StringTokenizer(code,tokenizer);//true��ݒ肷��Ƃ��������Ȃ�B
		//System.out.println("code : "+code +"; tokenizer : "+ tokenizer);
		Double result=null;
		while(st.hasMoreTokens()){
			String prey=st.nextToken();
			//System.out.println("prey:"+prey+";tokenizer:"+tokenizer);
			if((result==null) && prey.matches("^\\d+$")){
				result=Double.parseDouble(prey);
			}else{
				if(result==null){
					//���̎}�̐����𕪉��B
					result=(new MathTokenizer3(prey)).separate();
				}else{
					//�E�̎}�����B
					switch(tokenizer){
						case "+":
							result+=(new MathTokenizer3(prey)).separate();
							break;
						case "-":
							result-=(new MathTokenizer3(prey)).separate();
							break;
						case "*":
							result*=(new MathTokenizer3(prey)).separate();
							break;
						case "/":
							result/=(new MathTokenizer3(prey)).separate();
							break;
							//defalut��݂���ƃo�O�𔭐������Ă��܂��B
						case "^":
							Double exp =(new MathTokenizer3(prey)).separate();
							//System.out.println("MathPow���O result = " + result+";exp = " + exp);
							result = Math.pow(result, exp);
							//System.out.println("MathPow���� result = " + result);
							break;
						default:
							//System.out.println(tokenizer);
							break;
					}

				}

			}
		}



		return result;
	}
}
