public class Report {

	private String id;
	private Camp camp;
	private String fileType;
	private boolean print;

	public Report(String id, Camp camp, String fileType , boolean print){
		this.id = id;
		this.camp = camp;
		this.fileType = fileType; 
		this.print = print;
	}

	public String getId() {
		return this.id;
	}

	public Camp getCamp() {
		return this.camp;
	}

	public String getFileType() {
		return this.fileType;
	}

	public boolean getPrint() {
		return this.print;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @param camp
	 */
	public void setCamp(Camp camp) {
		this.camp = camp;
	}

	/**
	 * 
	 * @param fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 
	 * @param print
	 */
	public void setPrint(boolean print) {
		this.print = print;
	}

}