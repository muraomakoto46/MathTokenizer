package signChain;

import java.io.Serializable;

public class TroubleCode implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String troubleMathCode;
	public TroubleCode(String troubleMathCode){
		this.troubleMathCode= troubleMathCode;
	}

	public String getTroubleMathCode(){
		return this.troubleMathCode;
	}

	public void setTroubleMathCode(String troubleMathCode){
		this.troubleMathCode=troubleMathCode;
	}

	public String toString(){
		return this.troubleMathCode;
	}


}
