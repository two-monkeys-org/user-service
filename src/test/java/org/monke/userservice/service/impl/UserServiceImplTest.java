package org.monke.userservice.service.impl;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.monke.userservice.converter.RoleConverter;
import org.monke.userservice.dummy.DummyData;
import org.monke.userservice.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleConverter roleConverter;

    private final Faker faker = new Faker();
    private final DummyData dummyData = new DummyData();

}
