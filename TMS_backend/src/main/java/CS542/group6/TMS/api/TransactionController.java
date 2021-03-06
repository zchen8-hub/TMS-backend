package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
import CS542.group6.TMS.dto.TagDTO;
import CS542.group6.TMS.dto.TransactionDTO;
import CS542.group6.TMS.dto.UserDTO;
import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.model.Transaction;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.service.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public JsonResult<List<TransactionDTO>> listTransactions(@PathVariable String gid) {
        List<Transaction> transactions = transactionServices.listTransactions(gid);
        List<TransactionDTO> dtos = buildOutputTransactionDTOs(transactions);
        return new JsonResult<>(dtos);
    }

    @PostMapping("/group/{gid}/transaction")
    public JsonResult<TransactionDTO> addTransaction(@PathVariable String gid, @RequestBody TransactionDTO transactionDTO) {
        transactionDTO.setGroupId(gid);
        Transaction transaction = transactionServices.addTransaction(transactionDTO.convertToTransaction());

        TransactionDTO output = new TransactionDTO();
        output = output.convertFromTransaction(transaction);
        output.setTagDTOs(new ArrayList<>());
        output.setUserDTOs(buildOutputUserDTO(transaction.getUserList()));
        return new JsonResult<>(output);
    }

    @PutMapping("/group/{gid}/transaction/{tid}")
    public JsonResult<TransactionDTO> updateTransaction(@PathVariable String gid, @PathVariable String tid, @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionServices.updateTransaction(tid, gid, transactionDTO);

        TransactionDTO output = new TransactionDTO();
        output = output.convertFromTransaction(transaction);
        output.setUserDTOs(buildOutputUserDTO(transaction.getUserList()));
        return new JsonResult<>(output);
    }

    @DeleteMapping("/group/{gid}/transaction/{tid}")
    public JsonResult deleteTransaction(@PathVariable String gid, @PathVariable String tid) {
        String result = transactionServices.deleteTransaction(tid);
        return new JsonResult(result);
    }

    @GetMapping("/transaction/{tid}/users")
    public List<User> listTransactionUsers(@PathVariable String tid) {
        return transactionServices.listTransactionUsers(tid);
    }

    @PostMapping("/transaction/{tid}/user/{uid}")
    public JsonResult<TransactionDTO> addUserToTransaction(@PathVariable String tid, @PathVariable String uid) {
        Transaction transaction = transactionServices.addUserToTransaction(tid, uid);
        TransactionDTO dto = new TransactionDTO();
        dto = dto.convertFromTransaction(transaction);
        dto.setUserDTOs(buildOutputUserDTO(transaction.getUserList()));
        return new JsonResult<>(dto);
    }

    @DeleteMapping("/transaction/{tid}/user/{uid}")
    public JsonResult<TransactionDTO> deleteUserFromTransaction(@PathVariable String tid, @PathVariable String uid) {
        Transaction transaction = transactionServices.deleteUserFromTransaction(tid, uid);
        TransactionDTO dto = new TransactionDTO();
        dto = dto.convertFromTransaction(transaction);
        dto.setUserDTOs(buildOutputUserDTO(transaction.getUserList()));
        return new JsonResult<>(dto);
    }

    @GetMapping("/transaction/{tid}/tags")
    public List<Tag> listTags(@PathVariable String tid) {
        return transactionServices.listTags(tid);
    }

    @PostMapping("/transaction/{tid}/tag/{tagId}")
    public JsonResult<TransactionDTO> addTagToTransaction(@PathVariable String tid, @PathVariable String tagId) {
        Transaction transaction = transactionServices.addTagToTransaction(tid, tagId);
        TransactionDTO dto = new TransactionDTO();
        dto = dto.convertFromTransaction(transaction);
        dto.setTagDTOs(TagController.assembleTagDTOs(transaction.getTagList()));
        return new JsonResult<>(dto);
    }

    @DeleteMapping("/transaction/{tid}/tag/{tagId}")
    public JsonResult deleteTagFromTransaction(@PathVariable String tid, @PathVariable String tagId) {
        Transaction transaction = transactionServices.deleteTagFromTransaction(tid, tagId);
        return new JsonResult<>(transaction);
    }

    static List<TransactionDTO> buildOutputTransactionDTOs(List<Transaction> transactions) {
        List<TransactionDTO> dtos = new ArrayList<>();
        for (Transaction transaction : transactions) {
            List<UserDTO> userDTOs = buildOutputUserDTO(transaction.getUserList());
            List<TagDTO> tagDTOs = TagController.assembleTagDTOs(transaction.getTagList());
            TransactionDTO dto = new TransactionDTO();
            dto = dto.convertFromTransaction(transaction);
            dto.setUserDTOs(userDTOs);
            dto.setTagDTOs(tagDTOs);
            dtos.add(dto);
        }
        return dtos;
    }

    static List<UserDTO> buildOutputUserDTO(List<User> users){
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO = userDTO.convertFromUser(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
