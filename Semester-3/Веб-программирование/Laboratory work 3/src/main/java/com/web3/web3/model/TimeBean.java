package com.web3.web3.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;

@Named
@SessionScoped
@ManagedBean
public class TimeBean implements Serializable {

    public LocalDateTime getNowTime() {
        return LocalDateTime.now();
    }

}
