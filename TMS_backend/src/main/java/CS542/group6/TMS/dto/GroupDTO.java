package CS542.group6.TMS.dto;

import CS542.group6.TMS.model.Group;
import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;
import java.util.List;

public class GroupDTO {

    private String groupId;
    private String groupName;
    private String projectId;
    private List<TransactionDTO> transactions;

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    /**
     * Convert DTO to PO (Persistence object, completely match parameters of user table )
     * @return PO
     */
    public Group convertToGroup(){
        GroupDTOConvert groupDTOConvert = new GroupDTOConvert();
        return groupDTOConvert.convert(this);
    }

    public GroupDTO convertFromGroup(Group group) {
        GroupDTOConvert groupDTOConvert = new GroupDTOConvert();
        return groupDTOConvert.doBackward(group);
    }

    private static class GroupDTOConvert extends Converter<GroupDTO, Group> {

        @Override
        protected Group doForward(GroupDTO groupDTO) {
            Group group = new Group();
            BeanUtils.copyProperties(groupDTO, group);
            return group;
        }

        @Override
        protected GroupDTO doBackward(Group group) {
            GroupDTO dto = new GroupDTO();
            BeanUtils.copyProperties(group, dto);
            return dto;
        }
    }
}
