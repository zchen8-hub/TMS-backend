package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
import CS542.group6.TMS.dto.TagDTO;
import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.service.TagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public JsonResult<List<TagDTO>> getTags(@PathVariable String pid) {
        List<Tag> tags = tagServices.getTagsByProjectId(pid);
        List<TagDTO> dtos = assembleTagDTOs(tags);
        return new JsonResult<>(dtos);
    }

    @PostMapping("user/{uid}/project/{pid}/tag")
    public JsonResult<TagDTO> createTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @Valid @RequestBody TagDTO tagDTO) {
        tagDTO.setTagId(UUID.randomUUID().toString());
        tagDTO.setProjectId(pid);
        Tag tag = tagServices.createTag(uid, pid, tagDTO.convertToTag());
        TagDTO dto = new TagDTO();
        dto.setProjectId(tag.getProjectId());
        dto.setTagName(tag.getTagName());
        dto.setTagId(tag.getTagId());
        return new JsonResult<>(dto);
    }

    @PutMapping("user/{uid}/project/{pid}/tag/{tagId}")
    public JsonResult<TagDTO> updateTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("tagId") String tid, @Valid @RequestBody TagDTO tagDTO) {
        Tag tag = tagServices.updateTag(uid, pid, tid, tagDTO.convertToTag());
        TagDTO dto = new TagDTO();
        dto.setProjectId(tag.getProjectId());
        dto.setTagName(tag.getTagName());
        dto.setTagId(tag.getTagId());
        return new JsonResult<>(dto);
    }

    @DeleteMapping("user/{uid}/project/{pid}/tag/{tid}")
    public JsonResult deleteTag(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("tid") String tid) {
        String msg = tagServices.deleteTag(uid, pid, tid);
        return new JsonResult(msg);
    }

    static List<TagDTO> assembleTagDTOs(List<Tag> tagList) {
        List<TagDTO> dtos = new ArrayList<>();
        for (Tag tag : tagList) {
            TagDTO dto = new TagDTO();
            dto.setTagId(tag.getTagId());
            dto.setTagName(tag.getTagName());
            dto.setProjectId(tag.getProjectId());
            dtos.add(dto);
        }
        return dtos;
    }
}
