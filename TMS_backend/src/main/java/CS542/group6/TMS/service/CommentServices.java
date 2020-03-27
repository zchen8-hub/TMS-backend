package CS542.group6.TMS.service;

import CS542.group6.TMS.model.Comment;
import CS542.group6.TMS.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServices {
    private CommentRepository commentRepository;

    public CommentServices(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTransactionId(String tid) {
        return commentRepository.findALLByTransaction(tid);
    }

    public boolean deleteComment(String uid, String cid) {
        if (!commentRepository.getOne(cid).getCreaterId().equals(uid))
            return false;
        commentRepository.deleteById(cid);
        return true;
    }
}
