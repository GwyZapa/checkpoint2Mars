package br.com.fiap.checkpoint2Mars.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.checkpoint2Mars.models.Travels;

@Repository
public interface travelRepository extends JpaRepository<Travels, Long> {

}
