import com.services.bond.app.application.port.out.BondOutPort;
import com.services.bond.app.application.service.BondService;
import com.services.bond.app.application.service.exception.BondDuplicateException;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import com.services.bond.app.application.service.exception.ValueNegativeException;
import com.services.bond.app.domain.model.Bond;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BondServiceTest {

    @InjectMocks
    private BondService bondService;


    @Mock
    private BondOutPort bondOutPort;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetAllBonds_returnAllBondsTest() throws Exception {
        Optional<Bond> bondA = Optional.ofNullable(new Bond());
        bondA.get().setId(1);
        bondA.get().setName("Bond A");
        bondA.get().setPrice(100.0);
        bondA.get().setInterestRate(0.1);
        bondA.get().setCreationDate(LocalDate.now());

        Optional<Bond> bondB = Optional.ofNullable(new Bond());
        bondB.get().setId(2);
        bondB.get().setName("Bond B");
        bondB.get().setPrice(200.0);
        bondB.get().setInterestRate(0.2);
        bondB.get().setCreationDate(LocalDate.now());

        Optional<Bond> bondC = Optional.ofNullable(new Bond());
        bondC.get().setId(3);
        bondC.get().setName("Bond C");
        bondC.get().setPrice(300.0);
        bondC.get().setInterestRate(0.3);
        bondC.get().setCreationDate(LocalDate.now());

        List<Bond> bondList = new ArrayList<>();
        bondList.add(bondA.get());
        bondList.add(bondB.get());
        bondList.add(bondC.get());

        bondOutPort.save(bondA.get());
        when(bondOutPort.getAll()).thenReturn(bondList);

        List<Bond> result = bondService.getAll();
        assertEquals(result.size(), bondList.size());
    }

    @Test
    void whenGetAllBonds_returnEmptyListTest() throws Exception {
        List<Bond> bondList = new ArrayList<>();
        when(bondOutPort.getAll()).thenReturn(bondList);

        List<Bond> result = bondService.getAll();
        assertEquals(result.size(), bondList.size());
    }

    @Test
    void whenFindByIdBond_returnBondTest() throws Exception {
        Long inputId = 1L;

        Optional<Bond> bondEntity = Optional.ofNullable(new Bond());
        bondEntity.get().setId(inputId);
        bondEntity.get().setName("Bond A");
        bondEntity.get().setPrice(100.0);
        bondEntity.get().setInterestRate(0.1);
        bondEntity.get().setCreationDate(LocalDate.now());

        when(bondOutPort.findById(inputId)).thenReturn(bondEntity);

        Bond result = bondService.findById(inputId);
        assertEquals(result.getId(), bondEntity.get().getId());
    }

    @Test
    void whenFindByIdEmpty_returnBondNotFoundExceptionTest() throws Exception {
        Long inputId = 1L;
        when(bondOutPort.findById(inputId)).thenReturn(Optional.empty());
        assertThrows(BondNotFoundException.class, () -> bondService.findById(inputId));
    }

    @Test
    void whenCreateBond_returnBondTest() throws Exception {
        Bond bond = new Bond();
        bond.setName("Bond A");
        bond.setPrice(100.0);
        bond.setInterestRate(0.1);
        bond.setCreationDate(LocalDate.now());

        when(bondOutPort.save(bond)).thenReturn(bond);

        Bond result = bondService.create(bond);
        assertEquals(result.getName(), bond.getName());
    }

    @Test
    void whenCreateBond_returnBondDuplicateExceptionTest() throws Exception {
        Bond bond = new Bond();
        bond.setName("Bond A");
        bond.setPrice(100.0);
        bond.setInterestRate(0.1);
        bond.setCreationDate(LocalDate.now());

        Bond bond2 = new Bond();
        bond.setName("Bond A");
        bond.setPrice(100.0);
        bond.setInterestRate(0.1);
        bond.setCreationDate(LocalDate.now());

        when(bondOutPort.existsByNameIgnoreCase(bond.getName())).thenReturn(true);
        assertThrows(BondDuplicateException.class, () -> bondService.create(bond));
    }

    @Test
    void whenUpdateBond_returnBondTest() throws Exception {
        Long inputId = 1L;
        Bond bond = new Bond();
        bond.setId(inputId);
        bond.setName("Bond A");
        bond.setPrice(100.0);
        bond.setInterestRate(0.1);
        bond.setCreationDate(LocalDate.now());

        Optional<Bond> bondEntity = Optional.ofNullable(new Bond());
        bondEntity.get().setId(inputId);
        bondEntity.get().setName("Bond A");
        bondEntity.get().setPrice(100.0);
        bondEntity.get().setInterestRate(0.1);
        bondEntity.get().setCreationDate(LocalDate.now());

        when(bondOutPort.findById(inputId)).thenReturn(bondEntity);
        when(bondOutPort.save(bond)).thenReturn(bond);

        Bond result = bondService.update(inputId, bond);
        assertEquals(result.getId(), bond.getId());
    }

    @Test
    void whenUpdateBond_returnBondNotFoundExceptionTest() throws Exception {
        Long inputId = 1L;
        Bond bond = new Bond();
        bond.setId(inputId);
        bond.setName("Bond A");
        bond.setPrice(100.0);
        bond.setInterestRate(0.1);
        bond.setCreationDate(LocalDate.now());

        when(bondOutPort.findById(inputId)).thenReturn(Optional.empty());
        assertThrows(BondNotFoundException.class, () -> bondService.update(inputId, bond));
    }
    
    @Test
    void whenUpdateBond_returnValueNegativeExceptionTest() throws Exception {
        Long inputId = 1L;
        Bond bond = new Bond();
        bond.setId(inputId);
        bond.setName("Bond A");
        bond.setPrice(-100.0);
        bond.setInterestRate(0.1);
        bond.setCreationDate(LocalDate.now());

        Optional<Bond> bondEntity = Optional.ofNullable(new Bond());
        bondEntity.get().setId(inputId);
        bondEntity.get().setName("Bond A");
        bondEntity.get().setPrice(100.0);
        bondEntity.get().setInterestRate(0.1);
        bondEntity.get().setCreationDate(LocalDate.now());

        when(bondOutPort.findById(inputId)).thenReturn(bondEntity);
        assertThrows(ValueNegativeException.class, () -> bondService.update(inputId, bond));
    }

    @Test
    void whenDeleteBond_returnBondNotFoundExceptionTest() throws Exception {
        Long inputId = 1L;
        when(bondOutPort.findById(inputId)).thenReturn(Optional.empty());
        assertThrows(BondNotFoundException.class, () -> bondService.delete(inputId));
    }

    @Test
    void whenDeleteBond_returnVoidTest() throws Exception {
        Long inputId = 1L;
        Bond bond = new Bond();
        bond.setId(inputId);
        bond.setName("Bond A");
        bond.setPrice(100.0);
        bond.setInterestRate(0.1);
        bond.setCreationDate(LocalDate.now());

        Optional<Bond> bondEntity = Optional.ofNullable(new Bond());
        bondEntity.get().setId(inputId);
        bondEntity.get().setName("Bond A");
        bondEntity.get().setPrice(100.0);
        bondEntity.get().setInterestRate(0.1);
        bondEntity.get().setCreationDate(LocalDate.now());

        when(bondOutPort.findById(inputId)).thenReturn(bondEntity);

        bondService.delete(inputId);
    }

}
