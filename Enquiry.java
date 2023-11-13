public class Enquiry {
    public String id;
    public String subject;
    public String description;
    public boolean isProcessed;
    public String replyText;
    public String repliedByName;
    public boolean repliedByStaff;

    public String getDescription() {
        return description;
    }
    public String getId() {
        return id;
    }
    public String getSubject() {
        return subject;
    }
    public String getReplyText() {
        return replyText;
    }
    public String getRepliedByName() {
        return repliedByName;
    }
    public boolean getRepliedByStaff() {
        return repliedByStaff;
    }
    public boolean getIsProcessed() {
        return isProcessed;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }
    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
    public void setRepliedByName(String repliedByName) {
        this.repliedByName = repliedByName;
    }
    public void setRepliedByStaff(boolean repliedByStaff) {
        this.repliedByStaff = repliedByStaff;
    }

}
