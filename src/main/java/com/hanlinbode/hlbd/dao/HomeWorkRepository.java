package com.hanlinbode.hlbd.dao;

import com.hanlinbode.hlbd.bean.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWork,Long> {

    @Query("select h from HomeWork h where h.fk_teacher_id=:teacher_id")
    List<HomeWork> findHomeWorkByTeacherId(@Param("teacher_id") String teacherId);
}
