package com.example.ucstores.service;

import com.example.ucstores.data.exception.VendorException;
import com.example.ucstores.data.models.Product;
import com.example.ucstores.data.models.Vendor;
import com.example.ucstores.data.repository.ProductRepository;
import com.example.ucstores.data.repository.VendorRepository;
import com.example.ucstores.dtos.request.*;
import com.example.ucstores.dtos.response.VendorResponse;
import com.example.ucstores.validator.UserDetailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VendorServiceImpl implements VendorService{
    @Autowired
    VendorRepository vendorRepository;
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;


    @Override
    public VendorResponse registration(VendorRegistrationRequest request) {
        if (!UserDetailValidator.isValidEmailAddress(request.getEmailAddress())) throw new VendorException("Email not registered properly");
        if (!UserDetailValidator.isValidPhoneNumber(request.getPhoneNumber())) throw new VendorException("Invalid Phone Number");
        if (!UserDetailValidator.isValidPassword(request.getPassword())) throw new VendorException("Password not Strong");
     var savedVendor =   buildRegistration(request);

        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setMessage("Vendor successfully Registered");
        vendorResponse.setStatusCode(201);

        return vendorResponse;
    }

    private Vendor buildRegistration(VendorRegistrationRequest request) {
        Vendor vendor = new Vendor();
        vendor.setStoreName(vendor.getStoreName());
        vendor.setEmailAddress(request.getEmailAddress());
        vendor.setPassword(request.getPassword());
        vendorRepository.save(vendor);
        return vendor;
    }



    @Override
    public VendorResponse login(VendorLoginRequest request) {
        var foundEmail = vendorRepository.findByEmailAddress(request.getEmailAddress());
        if (foundEmail.getPassword().equals(request.getPassword())){
            VendorResponse response =  new VendorResponse();
            response.setMessage("Login Successful");
            response.setStatusCode(201);
            return response;
        }
        throw new VendorException("AUTHENTICATION FAILED");
    }

    @Override
    public VendorResponse update(VendorUpdateRequest request) {
    var foundVendor =    vendorRepository.findByEmailAddress(request.getEmailAddress());

    foundVendor.setAddress(request.getAddress());
    foundVendor.setPhoneNumber(request.getPhoneNumber());
    foundVendor.setEmailAddress(request.getEmailAddress());
    foundVendor.setPassword(request.getPassword());
    foundVendor.setStoreName(request.getStoreName());
    foundVendor.setFirstName(request.getFirstName());
    foundVendor.setLastName(request.getLastName());
    vendorRepository.save(foundVendor);

    VendorResponse vendorResponse = new VendorResponse();
    vendorResponse.setMessage("Update Successful");
    vendorResponse.setStatusCode(201);
        return vendorResponse;
    }

    @Override
    public VendorResponse delete(String id) {
        vendorRepository.deleteById(id);
        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setMessage("deleted Successful");
        vendorResponse.setStatusCode(201);
        return vendorResponse;
    }

    @Override
    public List<Vendor> getAllVendor() {
        return vendorRepository.findAll();
    }

    @Override
    public VendorResponse addProduct(VendorProductRequest addRequest) {
      var foundEmail =  vendorRepository.findById(addRequest.getVendorId()).orElseThrow(()-> new VendorException("ID NOT FOUND"));

        Product product = new Product();
        product.setName(addRequest.getName());
        product.setQuantity(addRequest.getQuantity());
        product.setPrice(addRequest.getPrice());
        product.setCategories(addRequest.getCategories());
        foundEmail.getAddProducts().add(product);

        productService.addVendorProduct(product);

        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setMessage("added Successful");
        vendorResponse.setStatusCode(201);
        return vendorResponse;
    }

    @Override
    public VendorResponse updateProduct(UpdateProductRequest updateProduct) {
        var foundId = productRepository.findById(updateProduct.getProductId()).orElseThrow(()-> new RuntimeException("ProductId not found"));
        foundId.setPrice(updateProduct.getPrice() != null
                ?updateProduct.getPrice() : foundId.getPrice());
        foundId.setCategories(updateProduct.getCategories() != null
                ?updateProduct.getCategories() : foundId.getCategories());
        foundId.setQuantity(updateProduct.getQuantity());
        foundId.setName(updateProduct.getName() != null && updateProduct.getName().equals("")
                ? updateProduct.getName() : foundId.getName());
        productRepository.save(foundId);
        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setMessage("Update Successful");
        vendorResponse.setStatusCode(201);
        return vendorResponse;
    }

}
