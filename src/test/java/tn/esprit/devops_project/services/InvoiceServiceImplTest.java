package tn.esprit.devops_project.services;

import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @Mock
    InvoiceRepository invoiceRepository;
    @Mock
    SupplierRepository supplierRepository;
    @Mock
    OperatorRepository operatorRepository;
    @InjectMocks
    InvoiceServiceImpl invoiceService;


    @Test
    void retrieveAllInvoices() {
        List<Invoice> invoiceList = Arrays.asList(
                new Invoice(1L, 10, 10, new Date(), new Date(), true, null, null),
                new Invoice(2L, 20, 20, new Date(), new Date(), true, null, null)
        );

        Mockito.when(invoiceRepository.findAll()).thenReturn(invoiceList);
        List<Invoice> results = invoiceService.retrieveAllInvoices();

        Mockito.verify(invoiceRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(2, results.size());
    }

    @Test
    void cancelInvoice() {
        Long id = 1L;
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(id);

        Mockito.when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));
        invoiceService.cancelInvoice(id);


        Mockito.verify(invoiceRepository, Mockito.times(1)).findById(id);
        Mockito.verify(invoiceRepository, Mockito.times(1)).save(invoice);
        Assertions.assertTrue(invoice.getArchived());
    }

    @Test
    void retrieveInvoice() {
        Long id = 1L;
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(id);

        Mockito.when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(invoice));
        Invoice result = invoiceService.retrieveInvoice(id);

        Mockito.verify(invoiceRepository, Mockito.times(1)).findById(id);
        Assertions.assertNotNull(result);
    }

    @Test
    void getInvoicesBySupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(supplierId);

        List<Invoice> invoiceList = Arrays.asList(
                new Invoice(1L, 10, 10, new Date(), new Date(), true, null, supplier),
                new Invoice(2L, 20, 20, new Date(), new Date(), true, null, supplier)
        );
        supplier.setInvoices(new HashSet<>(invoiceList));

        Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        List<Invoice> result = invoiceService.getInvoicesBySupplier(supplierId);

        Mockito.verify(supplierRepository, Mockito.times(1)).findById(supplierId);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void assignOperatorToInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(invoiceId);

        Long operatorId = 2L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        Mockito.when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));
        invoiceService.assignOperatorToInvoice(operatorId, invoiceId);

        Mockito.verify(invoiceRepository, Mockito.times(1)).findById(invoiceId);
        Mockito.verify(operatorRepository, Mockito.times(1)).findById(operatorId);
        Mockito.verify(operatorRepository, Mockito.times(1)).save(operator);
        Assertions.assertTrue(operator.getInvoices().contains(invoice));
    }

    @Test
    void getTotalAmountInvoiceBetweenDates() {
        Date startDate = new Date();
        Date endDate = new Date();
        float totalAmount = 100.0f;

        Mockito.when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(totalAmount);
        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        Mockito.verify(invoiceRepository, Mockito.times(1)).getTotalAmountInvoiceBetweenDates(startDate, endDate);
        Assertions.assertEquals(totalAmount, result);
    }
}
