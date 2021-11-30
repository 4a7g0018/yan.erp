package com.yan.easy.erp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class AjaxResponseBody {
    @Getter @Setter
    String msg;
    @Getter @Setter
    List<User> result;
}
