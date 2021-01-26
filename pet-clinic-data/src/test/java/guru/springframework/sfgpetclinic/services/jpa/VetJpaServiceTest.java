package guru.springframework.sfgpetclinic.services.jpa;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetJpaService vetJpaService;

    final Long ID = 3L;
    final String NAME = "Doc";
    Vet returnedVet;
    Set<Vet> vets = new HashSet<>();

    @BeforeEach
    void setUp() {
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());
        returnedVet=Vet.builder().id(4L).build();
    }

    @Test
    void findAll() {
        when(vetRepository.findAll()).thenReturn(vets);
        assertEquals(2, vetJpaService.findAll().size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnedVet));
        assertEquals(returnedVet.getId(), vetJpaService.findById(ID).getId());
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().id(5L).build();
        when(vetRepository.save(any())).thenReturn(returnedVet);
        assertNotNull(vetJpaService.save(vetToSave));
    }

    @Test
    void delete() {
        vetJpaService.delete(returnedVet);
        verify(vetRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        vetJpaService.deleteById(ID);
        verify(vetRepository, times(1)).deleteById(anyLong());
    }
}