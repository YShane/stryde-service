package com.stryde.webservice.model.repository.travel;

import com.stryde.webservice.model.domain.Stop;
import com.stryde.webservice.model.repository.BaseRepository;

public interface StopRepository extends BaseRepository<Stop, Long> {

    Stop getOne(Long StopId);
}
