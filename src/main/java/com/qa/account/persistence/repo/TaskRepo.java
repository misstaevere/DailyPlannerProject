package com.qa.account.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.account.persistence.domain.Task;

//ORM - obj relational mapper - converts java obj into tables (Jpa - java persistence API) uses constr to create the objects and then getters and setters to stick the values in
// SELECT *FROM tbl; it rebuilds these from each class itself so we don't have to repeat the code
@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

}
