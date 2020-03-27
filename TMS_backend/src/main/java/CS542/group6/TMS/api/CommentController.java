package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.CommentDTO;
import CS542.group6.TMS.model.Comment;
import CS542.group6.TMS.service.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api")
@RestController
public class CommentController {
    private CommentServices commentServices;

    @Autowired
    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @GetMapping("/user/{uid}/transaction/{tid}/comments")
    public List<Comment> getComments(@PathVariable("uid") String uid, @PathVariable("tid") String tid){
        return commentServices.getCommentsByTransactionId(tid);
    }

    @PostMapping("/user/{uid}/transaction/{tid}/comment")
    public Comment createComment(@PathVariable("uid") String uid, @PathVariable("tid") String tid, @Valid @RequestBody CommentDTO commentDTO){
        commentDTO.setTransactionId(tid);
        commentDTO.setCreaterId(uid);
        return commentServices.createComment(commentDTO.convertToComment());
    }

    @DeleteMapping("/user/{uid}/transaction/{tid}/comment/{cid}")
    public String deleteComment(@PathVariable("uid") String uid, @PathVariable("tid") String tid,@PathVariable("cid") String cid){
        if(commentServices.deleteComment(uid,cid))
            return "success";
        return "failed";
    }
}
