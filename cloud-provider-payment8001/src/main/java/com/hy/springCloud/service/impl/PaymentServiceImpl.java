package com.hy.springCloud.service.impl;

import com.hy.springCloud.dao.PaymentDao;
import com.hy.springCloud.entities.Payment;
import com.hy.springCloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
       return paymentDao.create(payment);
    }

    @Override
    public Payment selectById(Integer id) {
        return paymentDao.selectById(id);
    }
}
