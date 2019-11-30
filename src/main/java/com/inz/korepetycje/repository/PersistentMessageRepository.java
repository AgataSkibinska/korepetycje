package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersistentMessageRepository extends JpaRepository<Message, Long> {

    // pobieranie wszystkich wiadomosci po id tutora <- dodać grupowanie po studencie
    List<Message> findAllByAdvertisement_CreatedBy(long tutorId);

}
