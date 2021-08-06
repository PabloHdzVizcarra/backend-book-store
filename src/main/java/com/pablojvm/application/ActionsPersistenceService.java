package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

public interface ActionsPersistenceService {
    User saveUser(DataPostUser data);
}
