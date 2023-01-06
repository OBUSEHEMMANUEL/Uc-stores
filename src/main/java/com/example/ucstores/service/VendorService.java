package com.example.ucstores.service;

import com.example.ucstores.data.models.Vendor;
import com.example.ucstores.dtos.request.*;
import com.example.ucstores.dtos.response.VendorResponse;

import java.util.List;

public interface VendorService {
    VendorResponse registration(VendorRegistrationRequest request);
    VendorResponse login(VendorLoginRequest request);
    VendorResponse update(VendorUpdateRequest request);

    VendorResponse delete(String id);
    List<Vendor> getAllVendor();
    VendorResponse addProduct(VendorProductRequest updateRequest);

    VendorResponse updateProduct(UpdateProductRequest updateProduct);
}
