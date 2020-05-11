package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.CommentDTO;
import CS542.group6.TMS.dto.JsonResult;
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
    public JsonResult<List<Comment>> getComments(@PathVariable("uid") String uid, @PathVariable("tid") String tid) {
        List<Comment> comments = commentServices.getCommentsByTransactionId(tid);
        return new JsonResult<>(comments);
    }

    @PostMapping("/user/{uid}/transaction/{tid}/comment")
    public JsonResult<Comment> addComment(@PathVariable("uid") String uid, @PathVariable("tid") String tid, @Valid @RequestBody CommentDTO commentDTO) {
        commentDTO.setTransactionId(tid);
        commentDTO.setCreaterId(uid);
        Comment comment = commentServices.createComment(commentDTO.convertToComment());
        return new JsonResult<>(comment);
    }

    @DeleteMapping("/user/{uid}/transaction/{tid}/comment/{cid}")
    public JsonResult deleteComment(@PathVariable("uid") String uid, @PathVariable("tid") String tid, @PathVariable("cid") String cid) {
        String msg = commentServices.deleteComment(uid, cid);
        return new JsonResult(msg);
    }
}
