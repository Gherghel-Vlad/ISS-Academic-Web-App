package com.example.AcademicWebApp.Repositories;

import com.example.AcademicWebApp.Models.Faculty;
import com.example.AcademicWebApp.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FacultyRepo extends JpaRepository<Faculty, String>
{
    @Query("Select fid from faculty where name=?1")
    Integer findFidByName(String name);
}