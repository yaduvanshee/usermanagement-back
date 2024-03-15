package com.userManagement.userManagement.dao;

import com.userManagement.userManagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface AddressRepository extends JpaRepository<Address,Long> {
    ArrayList<Address> getAddressesByUserId(Long userId);
}
