package com.cashier.app.cashierApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cashier.app.cashierApp.Model.Entity.Printer;

public interface PrinterRepository extends CrudRepository<Printer, Long>{
    Printer findOneById(Integer id);
}
