package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
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
    public JsonResult<List<Tag>> getTags(@PathVariable String pid) {
        List<Tag> tags = tagServices.getTagsByProjectId(pid);
        return new JsonResult<>(tags);
    }

    @PostMapping("user/{uid}/project/{pid}/tag")
    public JsonResult<Tag> createTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @Valid @RequestBody TagDTO tagDTO) {
        tagDTO.setTagId(UUID.randomUUID().toString());
        tagDTO.setProjectId(pid);
        Tag tag = tagServices.createTag(uid, pid, tagDTO.convertToTag());
        return new JsonResult<>(tag);
    }

    @PutMapping("user/{uid}/project/{pid}/tag/{tid}")
    public JsonResult<Tag> updateTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("tid") String tid, @Valid @RequestBody TagDTO tagDTO) {
        Tag tag = tagServices.updateTag(uid, pid, tid, tagDTO.convertToTag());
        return new JsonResult<>(tag);
    }

    @DeleteMapping("user/{uid}/project/{pid}/tag/{tid}")
    public JsonResult deleteTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("tid") String tid) {
        String msg = (tagServices.deleteTag(uid, pid, tid));
        return new JsonResult(msg);
    }
}
