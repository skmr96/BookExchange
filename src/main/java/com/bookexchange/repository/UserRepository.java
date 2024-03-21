package com.bookexchange.repository;

import com.bookexchange.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("update User u set u.rewardPoints = :rewardPoints where u.userId= :userId")
    public void updateRewardPoints(@Param("rewardPoints") int rewardPoints, @Param("userId")Long userId);

    @Query("select u.rewardPoints from User u where u.userId = :userId")
    public int getRewardPoints(@Param("userId")Long userId);

    @Modifying
    @Transactional
    @Query("update User u set u.isVerified = :isVerified where u.userId= :userId")
    public void updateVerificationStatus(@Param("isVerified") boolean isVerified, @Param("userId")Long userId);
}
