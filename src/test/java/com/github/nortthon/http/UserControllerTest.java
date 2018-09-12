package com.github.nortthon.http;

import com.github.nortthon.entities.User;
import com.github.nortthon.http.contracts.UserContract;
import com.github.nortthon.http.converters.UserConverter;
import com.github.nortthon.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private UserRepository repository;

    @SpyBean
    private UserConverter converter;

    @Test
    public void testSaveUser() {
        Mockito.when(repository.save(any())).thenReturn(Mono.just(mockUser()));

        final UserContract contract = new UserContract();
        contract.setName("Lucas Augusto");
        contract.setEmail("nortthon@gmail.com");
        contract.setBirthday(LocalDate.of(1985, 10, 10));

        client.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(contract)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Lucas Augusto")
                .jsonPath("$.email").isEqualTo("nortthon@gmail.com")
                .jsonPath("$.birthday").isEqualTo("1985-10-10");
    }

    //@Test
    public void testFindAllUsers() {
        Mockito.when(repository.findAll()).thenReturn(Flux.just(mockUser()));

        client.get().uri("/user")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].name").isEqualTo("Lucas Augusto")
                .jsonPath("$[0].email").isEqualTo("nortthon@gmail.com")
                .jsonPath("$[0].birthday").isEqualTo("1985-10-10");
    }

    //@Test
    public void testFindUserById() {
        Mockito.when(repository.findById("9999999999999")).thenReturn(Mono.just(mockUser()));

        client.get().uri("/user/" + mockUser().getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Lucas Augusto")
                .jsonPath("$.email").isEqualTo("nortthon@gmail.com")
                .jsonPath("$.birthday").isEqualTo("1985-10-10");
    }

    @Test
    public void testUserNotFound() {
        Mockito.when(repository.findById("1234567890")).thenReturn(Mono.empty());

        client.get().uri("/user/1234567890")
                .exchange()
                .expectStatus().isNotFound();
    }

    private User mockUser() {
        final User user = new User();
        user.setId("9999999999999");
        user.setName("Lucas Augusto");
        user.setEmail("nortthon@gmail.com");
        user.setBirthday(LocalDate.of(1985, 10, 10));
        return user;
    }
}
