package com.scy.controller;

import com.scy.resoult.Resoult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/aa")
    @RequiresRoles("ADMIN")
    public Resoult getUserById() {
        System.out.println("被访问");
        return new Resoult(200,"",null);
    }
}
