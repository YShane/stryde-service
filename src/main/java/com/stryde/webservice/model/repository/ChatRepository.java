package com.stryde.webservice.model.repository;

import com.stryde.webservice.dto.chat.ChatMinDto;
import com.stryde.webservice.model.domain.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChatRepository extends BaseRepository<Chat, Long> {

        @Query(value = "SELECT u.user_id as userid, u.first_name as userfirstname, u.last_name as userlastname, u.username as username, m.profilephotothumbnail_link as profilephotolink, u.date_of_birth as dob\n" +
            "FROM chat_participant cp LEFT JOIN user u ON cp.user_user_id = u.user_id \n" +
            "LEFT JOIN chat c ON cp.chat_chat_id = c.chat_id LEFT JOIN media m ON u.user_id = m.user_id  WHERE cp.user_user_id != :userId",  nativeQuery = true)
        List<ChatDetails> getallChatsforUserwithId(@Param("userId") Long userId);


        static interface ChatDetails {
            Long getUserid();
            String getUserFirstname();
            String getUserlastname();
            String getUsername();
            String getProfilephotolink();
            LocalDateTime getUserdob();
        }


}
