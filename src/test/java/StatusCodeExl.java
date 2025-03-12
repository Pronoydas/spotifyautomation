
public enum StatusCodeExl {
	
	CODE_200(200 ,""),
	CODE_201(201,""),
	CODE_400(400,"Missing required field: name"),
	CODE_401(401,"Invalid Assess Token");
	
	
	
	public int i ;
	public String string;

	StatusCodeExl(int i, String string) {
		// TODO Auto-generated constructor stub
		this.i =i ;
		this.string = string;
	}

}
