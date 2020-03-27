package CS542.group6.TMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "invitation_code")
public class InviCode {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "code_id", updatable = false, nullable = false)
    private String codeId;

    @Column(name = "inviter_id", updatable = false, nullable = false)
    private String inviterId;

    @Column(name = "code_string", nullable = false)
    private String codeString;

    @Column(name = "project_id", updatable = false, nullable = false)
    private String projectId;

    public InviCode(){}

    public InviCode(String inviterId, String codeString, String projectId) {
        this.inviterId = inviterId;
        this.codeString = codeString;
        this.projectId = projectId;
    }

    public String getCodeId() {
        return codeId;
    }

    public String getInviterId() {
        return inviterId;
    }

    public String getCodeString() {
        return codeString;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public void setCodeString(String codeString) {
        this.codeString = codeString;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
