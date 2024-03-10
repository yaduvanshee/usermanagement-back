package com.userManagement.userManagement.service.interfaces;

import com.userManagement.userManagement.exception.AddressNotFoundException;
import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.model.Address;

import java.util.List;

public interface AddressService {
    Address getAddressById(Long id) throws AddressNotFoundException;

    Address createAddress(Address address) throws UserManagementException;

    Address updateAddress(Long id, Address updatedAddress) throws AddressNotFoundException;
    List<Address> getAddressesByUserId(Long userId);
}
