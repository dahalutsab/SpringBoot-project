package com.aadim.project.repository;

import com.aadim.project.entity.EnrollProgram;
import com.aadim.project.entity.Program;
import com.aadim.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EnrollProgramRepository extends JpaRepository<EnrollProgram, Integer> {

    @Query("SELECT u.role FROM User u WHERE u.userLogin.username = :username")
    EnrollProgram getAll(@Param("username") String username);

//    List<EnrollProgram> findByUserId(Integer userId);
    List<EnrollProgram> findByProgramId(Integer programId);

    boolean existsByUserAndProgram(User user, Program program);

    @Query("SELECT e FROM EnrollProgram e WHERE e.user.id = :userId")
    List<EnrollProgram> findByUserId(@Param("userId") Integer userId);

    @Query(
            nativeQuery = true,
            value = """
                    select u.id , u.full_name as "fullName" , u.email , u.contact_num as "phoneNumber" ,
                       ep.enrollment_date as "enrollmentDate" from enroll_program ep
                       join users u on ep.user_id  = u.id
                       where ep.program_id  = :pId
                    """
    )
    List<Map<String,Object>> getAllStudentsByProgramId(Integer pId);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "DELETE FROM enroll_program WHERE user_id =:userId")
    void deleteStudentById(Integer userId);
}

