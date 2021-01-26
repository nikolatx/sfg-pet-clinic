package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetJpaService petService;

    Set<Pet> pets = new HashSet<>();
    final Long ID=3L;
    final String NAME="SHEEP";
    Pet returnedPet;

    @BeforeEach
    void setUp() {
        pets.add(Pet.builder().id(1L).name("cat").build());
        pets.add(Pet.builder().id(2L).name("dog").build());
        returnedPet = Pet.builder().id(ID).name(NAME).build();;
    }

    @Test
    void findAll() {
        when(petRepository.findAll()).thenReturn(pets);
        assertEquals(2, petService.findAll().size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnedPet));
        assertEquals(returnedPet.getId(), petService.findById(ID).getId());
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(4L).build();
        when(petRepository.save(any())).thenReturn(returnedPet);
        assertNotNull(petService.save(petToSave));
    }

    @Test
    void delete() {
        petService.delete(returnedPet);
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petService.deleteById(ID);
        verify(petRepository, times(1)).deleteById(anyLong());
    }
}