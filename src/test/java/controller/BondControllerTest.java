package controller;

import com.services.bond.app.BondApplication;
import com.services.bond.app.application.port.in.BondInPort;
import com.services.bond.app.application.service.exception.BondDuplicateException;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.exceptionHandler.GlobalExceptionHandler;
import com.services.bond.app.infrastructure.adapter.in.web.BondController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BondApplication.class)
public class BondControllerTest {

    @Autowired
    private BondController bondController;

    @MockBean
    private BondInPort bondInPort;

    private MockMvc mockMvc;

    private final List<Bond> bonds = new ArrayList<>();

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(bondController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        bonds.add(new Bond(1L, "AL20", 100.0, 5.0, LocalDate.now()));
        bonds.add(new Bond(2L, "AL30", 200.0, 6.0, LocalDate.now()));
        bonds.add(new Bond(3L, "AL40", 300.0, 7.0, LocalDate.now()));

    }

    @Test
    void shouldFetchAllBondsSuccessAndHasSize3_whenFindAll() throws Exception {
        when(bondInPort.getAll()).thenReturn(bonds);

        this.mockMvc.perform(get("/api/bonds/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void shouldReturnEmptyList_whenNoBondsFound() throws Exception {
        when(bondInPort.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/bonds/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldFetchBondById_whenBondExists() throws Exception {
        final long bondId = 1L;
        Bond bond = bonds.getFirst();
        when(bondInPort.findById(bondId)).thenReturn(bond);

        mockMvc.perform(get("/api/bonds/{id}", bondId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("AL20"));
    }

    @Test
    void shouldReturnStatus404_whenNotFoundBondById() throws Exception {
        final long bondId = 1L;
        when(bondInPort.findById(bondId)).thenThrow(new BondNotFoundException());

        mockMvc.perform(get("/api/bonds/{id}", bondId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRegisterBond_whenBondIsValid() throws Exception {
        Bond bond = new Bond(4L, "AL50", 500.0, 8.0, LocalDate.now());
        when(bondInPort.create(any())).thenReturn(bond);

        mockMvc.perform(post("/api/bonds/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"AL50\",\"price\":500.0,\"interestRate\":8.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("AL50"));
    }

    @Test
    void shouldReturnConflict_whenBondWithSameNameExists() throws Exception {
        doThrow(new BondDuplicateException())
                .when(bondInPort).create(any());

        mockMvc.perform(post("/api/bonds/")
                        .contentType("application/json")
                        .content("{\"name\":\"AL20\",\"price\":400.0,\"interestRate\":7.0}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Bond is duplicated."));
    }

    @Test
    void shouldUpdateBondSuccessfully_whenValidBondAndId() throws Exception {
        final long bondId = 1L;
        Bond bond = new Bond(bondId,"AL20-Updated", 500.0, 9.0, LocalDate.now());
        when(bondInPort.update(eq(bondId), any())).thenReturn(bond);

        mockMvc.perform(put("/api/bonds/{id}", bondId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"AL20-Updated\",\"price\":500.0,\"interestRate\":9.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("AL20-Updated"));
    }

    @Test
    void shouldReturnNotFound_whenBondToUpdateDoesNotExist() throws Exception {
        final long bondId = 99L;
        when(bondInPort.update(eq(bondId), any()))
                .thenThrow(new BondNotFoundException());

        mockMvc.perform(put("/api/bonds/{id}", bondId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"AL99\",\"price\":500.0,\"interestRate\":9.0}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Bond not found."));
    }

    @Test
    void shouldReturnConflict_whenUpdatingBondWithDuplicateName() throws Exception {
        final long bondId = 1L;
        when(bondInPort.update(eq(bondId), any()))
                .thenThrow(new BondDuplicateException());

        mockMvc.perform(put("/api/bonds/{id}", bondId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"AL20\",\"price\":500.0,\"interestRate\":9.0}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Bond is duplicated."));
    }

    @Test
    void shouldDeleteBondSuccessfully_whenBondExists() throws Exception {
        final long bondId = 1L;
        doNothing().when(bondInPort).delete(bondId);

        mockMvc.perform(delete("/api/bonds/{id}", bondId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFound_whenBondToDeleteDoesNotExist() throws Exception {
        final long bondId = 99L;
        doThrow(new BondNotFoundException())
                .when(bondInPort).delete(bondId);

        mockMvc.perform(delete("/api/bonds/{id}", bondId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Bond not found."));
    }

}
