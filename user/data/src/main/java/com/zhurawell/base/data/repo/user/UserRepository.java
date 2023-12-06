package com.zhurawell.base.data.repo.user;

import com.zhurawell.base.data.model.user.DateAndImage;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.projection.user.UserLightView;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import static org.hibernate.jpa.QueryHints.*;

import javax.persistence.QueryHint;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>, UserExtendedRepository {

    List<User> findByRegistrationDateAfter(Date afterDate);

    Optional<User> findByEmail(String email);

    @EntityGraph(value = "g-user-role")
    Optional<User> findByEmailOrLogin(String email, String login);

    Optional<User> findByLogin(String login);

    @QueryHints({
            @QueryHint(name = HINT_CACHEABLE, value ="true"),
            @QueryHint(name = HINT_CACHE_REGION, value = "user.findByEmailOrLogin")})
    @Query("select u from User u where u.firstName like ?1")
    List<User> findAllByFirstName(String firstName);

    @Query("select new com.zhurawell.base.data.model.user.User(u.id) from User u where u.firstName like ?1")
    List<User> findAllByFirstNameLight(String firstName);

    @Query("select u.id as id, u.email as email, u.login as login from User u where u.firstName like ?1")
    List<UserLightView> findByFirstNameLight(String firstName);

    @Query("select u.id as id, u.email as email, u.login as login from User u where u.firstName like ?1")
    <T> T findByFirstNameLight(String firstName, Class<T> type);

    @Query("select u.image from User u where u.id = :userId")
    String findUserImage(@Param("userId") BigInteger userId);

    @Query("select u.registrationDate from User u where u.id = ?1")
    Date findUserRegistrationDate(BigInteger userId);

    @Query("select new com.zhurawell.base.data.model.user.DateAndImage(u.registrationDate, u.image) from User u where u.id = ?1")
    DateAndImage findUserRegistrationDateAndImage(BigInteger userId);

    @Query("update User u set u.image = :img where u.id = :userId")
    @Modifying // Need to use this annotation when you modify data
    void updateUserImage(@Param("userId") BigInteger userId, @Param("img") String img);
}