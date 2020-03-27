package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,String> {

    @Query("select t from Tag t where t.projectId = :pid")
    List<Tag> findByProjectId(@Param("pid") String pid);

    @Modifying
    @Transactional
    @Query("update Tag t set t.tagName = :tagName where t.tagId = :tagId")
    void updateTag(@Param("tagId") String tid,@Param("tagName") String tagName);
}
