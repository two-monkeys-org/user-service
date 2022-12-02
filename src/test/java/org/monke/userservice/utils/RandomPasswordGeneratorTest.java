package org.monke.userservice.utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class RandomPasswordGeneratorTest {
    private RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator(new Random());

    @Test
    void shouldGenerateRandomPassword() {
        List<String> passwords = IntStream.rangeClosed(1, 8)
                .mapToObj(i -> randomPasswordGenerator.generateRandomPassword())
                .collect(Collectors.toList());

        assertAll(
                ()-> Objects.equals(passwords.size(), 8),
                ()-> Objects.equals(Set.of(passwords).size(), passwords.size())
        );
    }
}