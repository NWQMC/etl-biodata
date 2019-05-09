package gov.acwi.wqp.etl.biodata.projectData;

public class BiodataProjectData {
    private Integer dwProjectId;
    private String organization;
    private String organizationName;
    private String projectId;
    private String projectName;
    private String projectAbstract;

    public Integer getDwProjectId() {
        return dwProjectId;
    }

    public void setDwProjectId(Integer dwProjectId) {
        this.dwProjectId = dwProjectId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAbstract() {
        return projectAbstract;
    }

    public void setProjectAbstract(String projectAbstract) {
        this.projectAbstract = projectAbstract;
    }
}
