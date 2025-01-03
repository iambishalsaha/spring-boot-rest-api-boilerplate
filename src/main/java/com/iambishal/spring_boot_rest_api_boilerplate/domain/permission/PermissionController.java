package com.iambishal.spring_boot_rest_api_boilerplate.domain.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    @GetMapping()
    public ResponseEntity<String> getPermissions() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
