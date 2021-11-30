package com.yan.easy.erp.repository;

import com.yan.easy.erp.model.BulletinBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard,Long> {

    @Query(value = "SELECT * FROM bulletin_board AS b WHERE b.department_id=1 OR b.department_id=?1",nativeQuery = true)
    List<BulletinBoard> findBulletinBoardByDepartmentId(Long departmentId);

}
