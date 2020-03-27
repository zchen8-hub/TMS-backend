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
        if (!projectRepository.findById(pid).get().getCreaterId().equals(uid))
            return null;
        return tagRepository.save(tag);
    }

    public Boolean updateTag(String uid, String pid, String tid, Tag tag) {
        Tag updateTag = tagRepository.findById(tid).get();
        if (updateTag == null || ! projectRepository.findById(pid).get().getCreaterId().equals(uid))
            return false;
        tagRepository.updateTag(tid,tag.getTagName());
        return true;
    }

    public boolean deleteTag(String uid, String pid, String tid) {
        if (!projectRepository.findById(pid).get().getCreaterId().equals(uid))
            return false;
        tagRepository.deleteById(tid);
        return true;
    }
}
