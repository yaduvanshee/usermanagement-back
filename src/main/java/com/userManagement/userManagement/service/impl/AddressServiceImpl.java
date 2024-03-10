package com.userManagement.userManagement.service.impl;

import com.userManagement.userManagement.dao.AddressRepository;
import com.userManagement.userManagement.dao.UserRepository;
import com.userManagement.userManagement.errorEnum.AddressErrorEnum;
import com.userManagement.userManagement.exception.AddressNotFoundException;
import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.mailer.EmailHelper;
import com.userManagement.userManagement.model.Address;
import com.userManagement.userManagement.model.User;
import com.userManagement.userManagement.response.ErrorResponse;
import com.userManagement.userManagement.service.interfaces.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final EmailHelper emailHelper;

    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, EmailHelper emailHelper, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.emailHelper = emailHelper;
        this.userRepository = userRepository;
    }

    @Override
    public Address getAddressById(Long id) throws AddressNotFoundException {
        final Optional<Address> optionalAddress = this.addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        }
        AddressErrorEnum error = AddressErrorEnum.INVALID_ADDRESS_ID;
        throw new AddressNotFoundException(
                new ErrorResponse(error.getErrorMsg() + id, error.getErrorCode(), false));

    }

    @Override
    public Address createAddress(Address address) throws UserManagementException {
        return this.addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, Address updatedAddress) throws AddressNotFoundException {
        Address existingAddress = this.addressRepository.findById(id)
                .orElseThrow(() -> {
                    AddressErrorEnum error = AddressErrorEnum.INVALID_ADDRESS_ID;
                    return new AddressNotFoundException(
                            new ErrorResponse(error.getErrorMsg() + id, error.getErrorCode(), false)
                    );
                });

        //TODO Handel it for null
        existingAddress.setFirstLine(updatedAddress.getFirstLine());
        existingAddress.setSecondLine(updatedAddress.getSecondLine());
        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setLandmark(updatedAddress.getLandmark());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setState(updatedAddress.getState());
        existingAddress.setPostalCode(updatedAddress.getPostalCode());
        existingAddress.setCountry(updatedAddress.getCountry());

        Optional<User> user = this.userRepository.findById(existingAddress.getUserId());
        emailHelper.sendAddressChange(user.get().getEmail());

        return addressRepository.save(existingAddress);
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.getAddressesByUserId(userId);
    }
}
