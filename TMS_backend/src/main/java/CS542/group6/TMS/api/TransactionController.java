package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
import CS542.group6.TMS.dto.TransactionDTO;
import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.model.Transaction;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.service.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class TransactionController {
    private TransactionServices transactionServices;

    @Autowired
    public TransactionController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

    @GetMapping("/group/{gid}/transactions")
    public JsonResult<List<Transaction>> listTransactions(@PathVariable String gid) {
        List<Transaction> transactions = transactionServices.listTransactions(gid);
        return new JsonResult<>(transactions);
    }

    @PostMapping("/group/{gid}/transaction")
    public JsonResult<Transaction> addTransaction(@PathVariable String gid, @RequestBody TransactionDTO transactionDTO) {
        transactionDTO.setGroupId(gid);
        Transaction transaction = transactionServices.addTransaction(transactionDTO.convertToTransaction());
        return new JsonResult<>(transaction);
    }

    @PutMapping("/group/{gid}/transaction/{tid}")
    public JsonResult<Transaction> updateTransaction(@PathVariable String gid, @PathVariable String tid, @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionServices.updateTransaction(tid, transactionDTO);
        return new JsonResult<>(transaction);
    }

    @DeleteMapping("/group/{gid}/transaction/{tid}")
    public JsonResult deleteTransaction(@PathVariable String gid, @PathVariable String tid) {
        String result = transactionServices.deleteTransaction(tid);
        return new JsonResult(result);
    }

    @GetMapping("/transaction/{tid}/users")
    public List<User> listTransactionUsers(@PathVariable String tid){
        return transactionServices.listTransactionUsers(tid);
    }

    @PostMapping("/transaction/{tid}/user/{uid}")
    public JsonResult<User> addUserToTransaction(@PathVariable String tid, @PathVariable String uid) {
        User user = transactionServices.addUserToTransaction(tid, uid);
        return new JsonResult<>(user);
    }

    @DeleteMapping("/transaction/{tid}/user/{uid}")
    public JsonResult<User> deleteUserFromTransaction(@PathVariable String tid, @PathVariable String uid) {
        User user = transactionServices.deleteUserFromTransaction(tid, uid);
        return new JsonResult<>(user);
    }

    @GetMapping("/transaction/{tid}/tags")
    public List<Tag> listTags(@PathVariable String tid){
        return transactionServices.listTags(tid);
    }

    @PostMapping("/transaction/{tid}/tag/{tagId}")
    public JsonResult<Transaction> addTagToTransaction(@PathVariable String tid, @PathVariable String tagId) {
        Transaction transaction = transactionServices.addTagToTransaction(tid, tagId);
        return new JsonResult<>(transaction);
    }

    @DeleteMapping("/transaction/{tid}/tag/{tagId}")
    public JsonResult deleteTagFromTransaction(@PathVariable String tid,@PathVariable String tagId){
        Transaction transaction = transactionServices.deleteTagFromTransaction(tid, tagId);
        return new JsonResult<>(transaction);
    }
}
