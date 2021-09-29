package guru.springframework.sfgpetclinic.services.springdatajpa;

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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    private static final Long PET_ID=1L;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService petService;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet=Pet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Pet> pets = new HashSet<>();
        pets.add(Pet.builder().id(1L).build());
        pets.add(Pet.builder().id(2L).build());
        pets.add(Pet.builder().id(3L).build());
        when(petRepository.findAll()).thenReturn(pets);
        Set<Pet> pets1 = petService.findAll();
        assertEquals(3, pets1.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));
        Pet pet1 = petService.findById(PET_ID);
        assertEquals(pet1.getId(), PET_ID);
    }

    @Test
    void save() {
        when(petRepository.save(any())).thenReturn(returnPet);
        assertEquals(PET_ID, petService.save(returnPet).getId());
        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        petService.delete(returnPet);
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        petService.deleteById(PET_ID);
        verify(petRepository).deleteById(anyLong());
    }
}