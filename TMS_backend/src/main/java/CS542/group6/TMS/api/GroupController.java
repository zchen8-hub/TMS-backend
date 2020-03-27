package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.GroupDTO;
import CS542.group6.TMS.model.Group;
import CS542.group6.TMS.service.GroupServices;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class GroupController {

    private GroupServices groupServices;

    @GetMapping("/project/{pid}/groups")
    public List<Group> getGroups(@PathVariable String pid){
        return  groupServices.getGroupsByProjectId(pid);
    }

    @PostMapping("/project/{pid}/group")
    public Group createGroup(@PathVariable String pid,@Valid @RequestBody GroupDTO groupDTO){
        groupDTO.setGroupId(UUID.randomUUID().toString());
        groupDTO.setProjectId(pid);
        return groupServices.createGroup(groupDTO.convertToGroup());
    }

    @PutMapping("/project/{pid}/group/{gid}")
    public String updateGroup(@PathVariable("pid") String pid,@PathVariable("gid") String gid, @Valid @RequestBody GroupDTO groupDTO){
        if(groupServices.updateGroup(pid,gid,groupDTO.convertToGroup()))
            return "success";
        return "failed";
    }

    @DeleteMapping("/project/{pid}/group/{gid}")
    public String deleteGroup(@PathVariable("pid") String pid, @PathVariable("gid")  String gid){
        if (groupServices.deleteGroup(gid))
            return "success";
        return "failed";
    }
}
