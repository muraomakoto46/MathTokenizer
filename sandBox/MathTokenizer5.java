package sandBox;

import java.io.IOException;
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
 * MathTokenizer4�ł�
 *    7*-4
 *    24/-3
 *    4^-3
 *    ���K�؂ɏ����ł���悤�ɂ��邱�Ƃ�ڕW�Ƃ��č��ꂽ�B
 *    ���ʁA������\�� - �� ���炩���� N�ɕϊ����A���̕����̐K��N�����t�����Ă���ΑΉ��ł���悤�ɂȂ����B
 *    7*-9 �� 7*9N �ɕϊ����Ă����Ăق����B
 *    -6.5/4 �Ȃ�� 6.5N/4 �ɕϊ����Ă����Ăق����B
 *���L�̌v�Z�͂ł��Ȃ��B
 * ���ʂ����p�ł��܂���B
 *  (19-7)*13
 *
 *  35.23N-7.89N =-27.3999999... Double�^�̐��x�͂ƂĂ��������ǁA�����ł͂Ȃ��B
 **/
public class MathTokenizer5 {
	private String mathCode;
	public static void main(String[] args) throws IOException {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String query="2.66";
		MathTokenizer5 mtz = new MathTokenizer5(query);
		System.out.println(mtz.separate());
	}

	public MathTokenizer5(String mathCode){
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

		//���������͂��ꂽ��ǂ�����̂��B

	}
	public Double separate(){
		Double result=Double.valueOf(0.0);
		/**
		 * ��n���ꂽmathCode�� �ǂ�� ���Z�q���܂܂�Ă��܂����B
		 * ���Z�q�ɉ����čł��K�؂Ȗ��߂������܂��傤�B
		 *
		 * */
		try{
			if(mathCode.isEmpty()){
				throw new IllegalArgumentException("�󕶎��� �����G���[");
			}

		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
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
			System.out.println("----");
			//�������A -�̒��O�� * / ^�̂����ꂩ���������番�����Ȃ��B
			result = separate(this.mathCode,"-");
			return result;
		}

		// 97^150 % 13 �̂悤�Ȍv�Z�����Ȃ���Ȃ�񂩂�A ���� ^�����D�揇�ʂ�Ⴍ���Ă����B
		if(mathCode.indexOf("%")!=-1){
			result = separate(this.mathCode,"%");
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


		if(mathCode.matches("^\\d+.{0,1}\\d*N$")){

			mathCode=mathCode.replaceAll("^(.*)(N)$", "$1");
			result = Double.parseDouble(mathCode);
			result = result * (-1);
			System.out.println("�����̂Ȃ�separate()�ŁA3.9N�̂悤��N�t���������Ɣ��肳��܂����B");
			return result;
		}

		//������͍Ō�ɏ����Ȃ���΂Ȃ�Ȃ��B
		/* mathCode.matches("^\\d+$") ���ƁA�����_���ԂɊ܂܂�Ă�����͔̂������Ȃ���񂩁B*/
		if(mathCode.matches("^\\d+.{0,1}\\d*$")){
			//System.out.println("���l");
			return Double.parseDouble(mathCode);
		}

		if(mathCode.matches("^[e]{1}[N]{0,1}$")){
			Double e =Math.E;
			if(mathCode.matches("[e]{1}N{1}")){
				e=e*(-1);
			}
			return e;
		}

		if(mathCode.matches("^[P]{1}[N]{0,1}$")){
			Double pi= Math.PI;
			if(mathCode.matches("^[P]{1}[N]{1}$")){
				pi= pi*(-1);
			}
			return pi;
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
		System.out.println("code : "+code +"; tokenizer : "+ tokenizer);
		Double result=null;
		while(st.hasMoreTokens()){
			String prey=st.nextToken();
			System.out.println("prey:"+prey+";tokenizer:"+tokenizer+";code:"+code + "result="+result);
			if((result==null) && prey.matches("^(\\d+)([.]*)(\\d*)$")){
				result=Double.parseDouble(prey);

			}else if((result==null) && prey.matches("^(\\d+)([.]*)(\\d*)([N]{1})$")){
				prey=prey.replaceAll("^(.*)(N)$", "$1");
				result=Double.parseDouble(prey);
				result=result*(-1);
				System.out.println("3.9N �̂悤�Ȑ���������Ă������B");
			}else{

				if(result==null){
					//���̎}�̐����𕪉��B
					System.out.println("���̐����𕪉� code:"+prey);
					result=(new MathTokenizer5(prey)).separate();
				}else{
					//�E�̎}�����B
					switch(tokenizer){
						case "+":
							result+=(new MathTokenizer5(prey)).separate();
							break;
						case "-":
							result-=(new MathTokenizer5(prey)).separate();
							break;
						case "*":
							result*=(new MathTokenizer5(prey)).separate();
							break;
						case "/":
							result/=(new MathTokenizer5(prey)).separate();
							break;
							//defalut��݂���ƃo�O�𔭐������Ă��܂��B
						case "^":
							result = Math.pow(result, (new MathTokenizer5(prey)).separate());
							break;
						case "%":
							Double reminder=(new MathTokenizer5(prey).separate());
							result = Math.IEEEremainder(result, reminder);
							if(result.compareTo(Double.valueOf("0"))<0){
								result=result*(-1);
								result = reminder-result;
							}
							break;
						default:
							System.out.println(tokenizer);
							break;
					}

				}

			}
		}



		return result;
	}
}
