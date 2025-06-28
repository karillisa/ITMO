



package com.web3.web3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.web3.web3.model.AreaResultChecker.*;

public class AreaChecker {
    @Test
    public void test() throws SQLException {
        // Точка вне окружности радиуса 3
        Assertions.assertFalse(getResult(1.0,1.0,2.0));
    }
}

