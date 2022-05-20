package com.example.AcademicWebApp.Repositories;

import com.example.AcademicWebApp.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepo extends JpaRepository<Student, String>
{
    @Query("from student where group1 = ?1")
    List<Student> findByFirstGroup(Integer group1);

    List<Student> findAllByGroup1(Integer group1);

    List<Student> findAllByGroup2(Integer group2);

    @Query("from student where group2 = ?1")
    List<Student> findBySecondGroup(Integer group2);

    @Query("FROM student as s " +
            "INNER JOIN studentenrollment as se ON s.username = se.username " +
            "WHERE (se.fid = ?1 OR ?1 = '') AND (se.year = ?2 OR ?2 = '') AND (s.group1 = ?3 OR s.group2 = ?3 OR ?3 = '')")
    List<Student> findAllByFacultyYearGroup(int faculty, int year, int group);
}