package com.eventos.repository;

import com.eventos.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Event repository impl
 *
 * @author Alberto Zapardiel Fernández
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}
