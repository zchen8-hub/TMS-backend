package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    @Transactional
    @Modifying
    @Query("delete from Project p where p.projectId = ?1")
    void deleteProjectByProjectId(String projectId);
}
