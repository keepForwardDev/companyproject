package com.doctortech.fhq.service.business;

import com.doctortech.fhq.repository.mapper.business.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userDao;



}
