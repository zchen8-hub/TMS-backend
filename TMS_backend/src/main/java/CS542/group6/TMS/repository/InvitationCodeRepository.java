package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.InviCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvitationCodeRepository extends JpaRepository<InviCode, String> {
    @Query("select code from InviCode code where code.codeString = ?1")
    InviCode findByCodeString (String code);
}
