package com.stryde.webservice.model.repository;

import com.stryde.webservice.model.domain.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

public interface ChatRepository extends BaseRepository<Chat, Long> {

    @Query(value = "SELECT u.user_id as userid, u.first_name as userfirstname, u.last_name as userlastname, u.username as username, m.profilephotothumbnail_link as profilephotolink, u.date_of_birth as dob\n" +
            "FROM chat_participant cp LEFT JOIN user u ON cp.user_user_id = u.user_id \n" +
            "LEFT JOIN chat c ON cp.chat_chat_id = c.chat_id LEFT JOIN media m ON u.user_id = m.user_id  WHERE cp.user_user_id != :userId", nativeQuery = true)
    List<Object[]> findallChatsforUserwithId(@Param("userId") Long userId);

    @Override
    Optional<Chat> findById(Long aLong);
}
