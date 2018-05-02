package com.stryde.webservice.model.repository.travel;

import com.stryde.webservice.model.domain.Trip;
import com.stryde.webservice.model.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TripRepository extends BaseRepository<Trip, Long> {

    @Override
    Optional<Trip> findById(Long aLong);

    Optional<Trip> findByLineAndTrajectoryDateAdded(String line, LocalDateTime dateAdded);
}
