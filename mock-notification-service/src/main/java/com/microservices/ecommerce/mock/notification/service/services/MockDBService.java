package com.microservices.ecommerce.mock.notification.service.services;

import com.microservices.ecommerce.mock.notification.service.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MockDBService {

    public ArrayList<User> getUsersWithId(ArrayList<Long> ids) {
        return new ArrayList<User>();
    }

    public void updateUser(User user) {

    }
}
