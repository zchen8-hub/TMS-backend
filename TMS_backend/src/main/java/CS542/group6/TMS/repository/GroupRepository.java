package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends JpaRepository <Group, String> {

    @Query("select g from Group g where g.projectId = :pid")
    List<Group> findByProjectId(@Param("pid") String pid);

}
