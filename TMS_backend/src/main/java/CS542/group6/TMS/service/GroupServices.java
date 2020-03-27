package CS542.group6.TMS.service;

import CS542.group6.TMS.dto.GroupDTO;
import CS542.group6.TMS.model.Group;
import CS542.group6.TMS.repository.GroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServices {
    private GroupRepository groupRepository;

    public GroupServices(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group newGroup) {
        return groupRepository.save(newGroup);
    }

    public List<Group> getGroupsByProjectId(String pid) {
        return groupRepository.findByProjectId(pid);
    }

    public Group updateGroup(String pid, String gid, GroupDTO groupDTO) {
        Group group = groupRepository.findById(gid).get();
        group.setGroupName(groupDTO.getGroupName());
        return groupRepository.save(group);
    }

    public boolean deleteGroup(String gid) {
        groupRepository.deleteById(gid);
        return true;
    }
}
