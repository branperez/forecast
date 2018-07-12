package me.brandonmichael.forcast.models.data;

import me.brandonmichael.forcast.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface LocationDao extends CrudRepository<Location, Integer> {
}
