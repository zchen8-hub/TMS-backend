package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.TransactionDTO;
import CS542.group6.TMS.model.Transaction;
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
    public List<Transaction> listTransactions(@PathVariable String gid){
        return transactionServices.listTransactions(gid);
    }

    @PostMapping("/group/{gid}/transaction")
    public Transaction addTransaction(@PathVariable String gid, @RequestBody TransactionDTO transactionDTO){
        transactionDTO.setGroupId(gid);
        return transactionServices.addTransaction(transactionDTO.convertToTransaction());
    }

    @PutMapping("/group/{gid}/transaction/{tid}")
    public Transaction updateTransaction(@PathVariable String gid, @PathVariable String tid, @RequestBody TransactionDTO transactionDTO){
        return transactionServices.updateTransaction(tid, transactionDTO);
    }

    @DeleteMapping("/group/{gid}/transaction/{tid}")
    public String deleteTransaction(@PathVariable String gid, @PathVariable String tid){
        return transactionServices.deleteTransaction(tid);
    }
}
