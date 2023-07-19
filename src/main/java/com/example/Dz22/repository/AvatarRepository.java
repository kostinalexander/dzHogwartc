package com.example.Dz22.repository;

import com.example.Dz22.model.Avatar;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar,Long> {
    Optional<Avatar> findByStudentId(Long studentId);
    List<Avatar> findAll();
}
