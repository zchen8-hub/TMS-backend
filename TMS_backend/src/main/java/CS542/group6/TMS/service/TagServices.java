package CS542.group6.TMS.service;

import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServices {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    public TagServices(TagRepository tagRepository, ProjectRepository projectRepository) {
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
    }

    public List<Tag> getTagsByProjectId(String pid) {
        return tagRepository.findByProjectId(pid);
    }

    public Tag createTag(String uid, String pid, Tag tag) {
        if (!projectRepository.getOne(pid).getCreaterId().equals(uid))
            return null;
        return tagRepository.save(tag);
    }

    public Tag updateTag(String uid, String pid, String tid, Tag tag) {
        Tag updatedTag = tagRepository.getOne(tid);
        if (!projectRepository.getOne(pid).getCreaterId().equals(uid))
            return null;
        updatedTag.setTagName(tag.getTagName());
        return tagRepository.save(updatedTag);
    }

    public String deleteTag(String uid, String pid, String tid) {
        if (!projectRepository.getOne(pid).getCreaterId().equals(uid))
            return "Permission Denied";
        tagRepository.deleteById(tid);
        return "Success";
    }
}
