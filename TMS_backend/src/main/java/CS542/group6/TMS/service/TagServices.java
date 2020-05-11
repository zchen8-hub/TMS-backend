package CS542.group6.TMS.service;

import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.model.Transaction;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.TagRepository;
import CS542.group6.TMS.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServices {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final TransactionRepository transactionRepository;

    public TagServices(TagRepository tagRepository, ProjectRepository projectRepository, TransactionRepository transactionRepository) {
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
        this.transactionRepository = transactionRepository;
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
        Optional<Tag> updatedTag = tagRepository.findById(tag.getTagId());
        Transaction transaction = transactionRepository.getOne(tid);
        if (updatedTag.isPresent()){
            transaction.getTagList().add(updatedTag.get());
            updatedTag.get().getTransactionList().add(transaction);
            return updatedTag.get();
        }
        return null;
    }

    public String deleteTag(String uid, String pid, String tid) {
        if (!projectRepository.getOne(pid).getCreaterId().equals(uid))
            return "Permission Denied";
        tagRepository.deleteById(tid);
        return "Success";
    }
}
