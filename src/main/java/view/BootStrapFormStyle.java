package view;

public enum BootStrapFormStyle {
	DEFAULT("control-group"),
	ERROR("control-group error");
	
	private String styleClass;
	
	private BootStrapFormStyle(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public String getStyleClass() {
		return styleClass;
	}
}
