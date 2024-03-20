package uz.oauth2.oauth2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.oauth2.oauth2.Entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {
}
