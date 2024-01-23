package myDispatcher;


// ** ViewResolver
// => ViewName 을 완성
public class MyViewResolver {
	
	private String prefix;
	private String suffix;
	
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getViewName(String viewName) {
		System.out.println("** before viewName => "+ viewName);
		viewName = prefix + viewName +suffix;
		System.out.println("** after viewName" + viewName);
		return viewName;
		
	}
	
} // class