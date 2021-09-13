package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService vetService;

    Vet returnedVet;

    @BeforeEach
    void setUp() {
        returnedVet=Vet.builder().id(1L).build();
        vetService.save(returnedVet);
    }

    @Test
    void findAll() {
        Set<Vet> vets = new HashSet<>();
        vets.add(returnedVet);
        when(vetRepository.findAll()).thenReturn(vets);
        assertEquals(1, vetService.findAll().size());
        verify(vetRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnedVet));
        assertNotNull(vetService.findById(1L));
    }

    @Test
    void save() {
        when(vetRepository.save(any())).thenReturn(returnedVet);
        assertNotNull(vetService.save(returnedVet));
    }

    @Test
    void delete() {
        vetService.delete(returnedVet);
        verify(vetRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        vetService.deleteById(1L);
        verify(vetRepository, times(1)).deleteById(anyLong());
    }
}