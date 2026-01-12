package br.com.felipe.Treinamento.service;

import br.com.felipe.Treinamento.exceptionReponse.ResourceNotFoundException;
import br.com.felipe.Treinamento.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.felipe.Treinamento.repository.PersonRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public Person create(Person person){
        logger.info("Crate Pessoa");
        return  person;
    }

   public Person update(Person person){
       logger.info("Update Pessoa");

       Person entity = repository.findById(person.getId())
               .orElseThrow(() -> new ResourceNotFoundException("No record found for this id"));

       entity.setFirstName(person.getFirstName());
       entity.setLastName(person.getLastName());
       entity.setGender(person.getGender());
       entity.setAddress(person.getAddress());

       return repository.save(entity);
   }

   public Person findById(Long id){
       logger.info("list por id");
       return repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("No record found for this id"));
   }

   public List<Person> findAll(){
       logger.info("lista todas");
        return repository.findAll();
   }

    public void delete(Long id){
        logger.info("Delete Pessoa");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id"));

        repository.delete(entity);
    }

}
