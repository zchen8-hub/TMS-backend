package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.GroupDTO;
import CS542.group6.TMS.dto.JsonResult;
import CS542.group6.TMS.model.Group;
import CS542.group6.TMS.service.GroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class GroupController {

    private GroupServices groupServices;

    @Autowired
    public GroupController(GroupServices groupServices) {
        this.groupServices = groupServices;
    }

    @GetMapping("/project/{pid}/groups")
    public JsonResult<List<GroupDTO>> getGroups(@PathVariable String pid) {
        List<Group> groups = groupServices.getGroupsByProjectId(pid);
        List<GroupDTO> groupDTOS = new ArrayList<>();
        for (Group group : groups){
            GroupDTO dto = new GroupDTO();
            dto = dto.convertFromGroup(group);
            dto.setTransactions(TransactionController.buildOutputTransactionDTOs(group.getTransactionList()));
            groupDTOS.add(dto);
        }
        return new JsonResult<>(groupDTOS);
    }

    @PostMapping("/project/{pid}/group")
    public JsonResult<Group> createGroup(@PathVariable("pid") String pid, @Valid @RequestBody GroupDTO groupDTO) {
        groupDTO.setProjectId(pid);
        Group group = groupServices.createGroup(groupDTO.convertToGroup());
        return new JsonResult<>(group);
    }

    @PutMapping("/project/{pid}/group/{gid}")
    public JsonResult<Group> updateGroup(@PathVariable("pid") String pid, @PathVariable("gid") String gid, @Valid @RequestBody GroupDTO groupDTO) {
        Group group = groupServices.updateGroup(pid, gid, groupDTO);
        return new JsonResult<>(group);
    }

    @DeleteMapping("/project/{pid}/group/{gid}")
    public JsonResult deleteGroup(@PathVariable("pid") String pid, @PathVariable("gid") String gid) {
        String msg = groupServices.deleteGroup(gid);
        return new JsonResult(msg);
    }
}
