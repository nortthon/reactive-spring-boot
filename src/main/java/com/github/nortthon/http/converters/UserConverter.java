package com.github.nortthon.http.converters;

import com.github.nortthon.entities.User;
import com.github.nortthon.http.contracts.UserContract;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserContract convert(final User user) {
        final UserContract contract = new UserContract();
        contract.setName(user.getName());
        contract.setEmail(user.getEmail());
        contract.setBirthday(user.getBirthday());
        return contract;
    }

    public User convert(final UserContract contract) {
        final User user = new User();
        user.setName(contract.getName());
        user.setEmail(contract.getEmail());
        user.setBirthday(contract.getBirthday());
        return user;
    }
}
