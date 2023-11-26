public interface StaffReportInterface {
    public void generateReportOfStudentsAttendingSelfCreatedCamp(Staff staff);
    public void generatePerformanceReportOfCampCommitteeMembers(Staff staff);
    public void generateReportWithFilterByCampName(Staff staff,String name);
    public void generateReportFilterByLocation(Staff staff,String location);
    public void generateReportOfCCMAttendingSelfCreatedCamp(Staff staff);
}