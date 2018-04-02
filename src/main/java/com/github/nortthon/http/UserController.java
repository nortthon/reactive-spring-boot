package com.github.nortthon.http;

import com.github.nortthon.exceptions.UserNotFoundException;
import com.github.nortthon.http.contracts.UserContract;
import com.github.nortthon.http.converters.UserConverter;
import com.github.nortthon.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    private final UserConverter converter;

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<UserContract> save(@RequestBody final UserContract user) {
        return repository.save(converter.convert(user))
                .map(converter::convert);
    }

    @GetMapping
    public Flux<UserContract> getAll() {
        return repository.findAll()
                .map(converter::convert);
    }

    @GetMapping("/{id}")
    public Mono<UserContract> getById(@PathVariable("id") final String id) {
        return repository.findById(id)
                .map(converter::convert)
                .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }
}
