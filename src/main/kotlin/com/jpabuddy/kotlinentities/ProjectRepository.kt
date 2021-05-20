package com.jpabuddy.kotlinentities;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component("OpenProjectRepository")
interface ProjectRepository : JpaRepository<Project, Long> {
}