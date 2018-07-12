package me.brandonmichael.forcast.models.data;


import me.brandonmichael.forcast.models.Security.Password;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PasswordDao extends CrudRepository<Password, Integer> {

}
