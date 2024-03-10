package com.userManagement.userManagement.controller;

import com.userManagement.userManagement.exception.AddressNotFoundException;
import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.model.Address;
import com.userManagement.userManagement.service.interfaces.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddressById(@PathVariable Long addressId) throws AddressNotFoundException {
        final Address address = this.addressService.getAddressById(addressId);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAddressByUserId(@PathVariable Long userId) throws AddressNotFoundException {
        final List<Address> addresses = this.addressService.getAddressesByUserId(userId);
        return ResponseEntity.ok().body(addresses);
    }
    @PostMapping("/")
    public ResponseEntity<?> createAddress(@RequestBody Address address) throws UserManagementException {
        Address createdAddress = this.addressService.createAddress(address);
        return ResponseEntity.ok().body(createdAddress);
    }

    @PostMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable Long addressId, @RequestBody Address address) throws UserManagementException {
        Address updatedAddress = this.addressService.updateAddress(addressId, address);
        return ResponseEntity.ok().body(updatedAddress);
    }
}
