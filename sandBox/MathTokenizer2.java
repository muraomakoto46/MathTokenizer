package sandBox;

import java.util.StringTokenizer;

/**
 * �����񐔎����Z
 * �ċA�I�ɋ��߂ď�ʂ�long�l��ԋp���悤�Ƃ���B
 * �������ʂ����߂邽�߂̕ϐ��� �����Ƃ����G���Ă��Ȃ��B
 * �ێ琫�Ɗg�������F�߂��Ȃ��\�[�X�R�[�h���B
 *USAGE �g�p�@:
 *String mathCode="987/65-4*3+2-1"
 *MathTokenizer mt = new MathTokenizer(mathCode);
 *long result = mt.separete();
 *System.out.println("result = " + result);*/
public class MathTokenizer2 {
	private String mathCode;

	public MathTokenizer2(String mathCode){
		this.mathCode=mathCode;
		//mathCode���� ���O�ɋ󔒕����Ȃǂ��������Ă����K�v������B
	}
	public long separate(){
		long result=0;
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

		//������͍Ō�ɏ����Ȃ���΂Ȃ�Ȃ��B
		if(mathCode.matches("^\\d+$")){
			return Long.parseLong(mathCode);
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
	private long separate(String code,String tokenizer){
		StringTokenizer st = new StringTokenizer(code,tokenizer);
		//System.out.println("code : "+code +"; tokenizer : "+ tokenizer);
		Long result=null;
		while(st.hasMoreTokens()){
			String prey=st.nextToken();
			//System.out.println("prey:"+prey+";tokenizer:"+tokenizer);
			if((result==null) && prey.matches("^\\d+$")){
				result=Long.valueOf(prey);
			}else{
				if(result==null){
					result=(new MathTokenizer2(prey)).separate();
				}else{
					//�����Ɏ}�̍��E�ɉ����� �����������A�V�X�g�ł���΂����̂����B
					switch(tokenizer){
						case "+":
							result+=(new MathTokenizer2(prey)).separate();
							break;
						case "-":
							result-=(new MathTokenizer2(prey)).separate();
							break;
						case "*":
							result*=(new MathTokenizer2(prey)).separate();
							break;
						case "/":
							result/=(new MathTokenizer2(prey)).separate();
							break;
							//defalut��݂���ƃo�O�𔭐������Ă��܂��B
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
