public class DateTime {

	private String date;
	private String time;

	public DateTime() {
		this(null,null);
    }

    public DateTime(String date, String time) {
        this.date = date;
        this.time = time;
    }


	public String getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	/**
	 * 
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}

}