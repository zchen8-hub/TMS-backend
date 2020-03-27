package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.TagDTO;
import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.service.TagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class TagController {

    private TagServices tagServices;

    @Autowired
    public TagController(TagServices tagServices) {
        this.tagServices = tagServices;
    }

    @GetMapping("/project/{pid}/tags")
    public List<Tag> getTags(@PathVariable String pid) {
        return tagServices.getTagsByProjectId(pid);
    }

    @PostMapping("user/{uid}/project/{pid}/tag")
    public Tag createTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @Valid @RequestBody TagDTO tagDTO) {
        tagDTO.setTagId(UUID.randomUUID().toString());
        tagDTO.setProjectId(pid);
        return tagServices.createTag(uid, pid, tagDTO.convertToTag());
    }

    @PutMapping("user/{uid}/project/{pid}/tag/{tid}")
    public String updateTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("tid") String tid, @Valid @RequestBody TagDTO tagDTO) {
        if (tagServices.updateTag(uid, pid, tid, tagDTO.convertToTag()))
            return "success";
        return "failed";
    }

    @DeleteMapping("user/{uid}/project/{pid}/tag/{tid}")
    public String deleteTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("tid") String tid) {
        if (tagServices.deleteTag(uid, pid, tid))
            return "success";
        return "failed";
    }
}
