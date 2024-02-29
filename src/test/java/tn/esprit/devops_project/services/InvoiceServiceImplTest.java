package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Test
    void retrieveAllInvoices() {
        // Mocking the behavior of the repository
        Mockito.when(invoiceRepository.findAll()).thenReturn(Arrays.asList(new Invoice(), new Invoice()));

        // Testing the method
        List<Invoice> invoices = invoiceService.retrieveAllInvoices();

        // Assertions
        assertEquals(2, invoices.size());
    }

    @Test
    void cancelInvoice() {
        // Mocking the behavior of the repository
        Invoice invoice = new Invoice();
        Mockito.when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        // Testing the method
        invoiceService.cancelInvoice(1L);

        // Assertions
        Mockito.verify(invoiceRepository).save(invoice);
    }

    @Test
    void retrieveInvoice() {
        // Mocking the behavior of the repository
        Invoice expectedInvoice = new Invoice();
        Mockito.when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(expectedInvoice));

        // Testing the method
        Invoice actualInvoice = invoiceService.retrieveInvoice(1L);

        // Assertions
        assertEquals(expectedInvoice, actualInvoice);
    }

    @Test
    void getInvoicesBySupplier() {
        // Mocking the behavior of the repository
        Supplier supplier = new Supplier();
        Mockito.when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(supplier));
        Mockito.when(supplier.getInvoices()).thenReturn(new HashSet<>(Arrays.asList(new Invoice(), new Invoice())));

        // Testing the method
        List<Invoice> invoices = invoiceService.getInvoicesBySupplier(1L);

        // Assertions
        assertEquals(2, invoices.size());
    }

    @Test
    void assignOperatorToInvoice() {
        // Mocking the behavior of the repositories
        Invoice invoice = new Invoice();
        Operator operator = new Operator();
        Mockito.when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
        Mockito.when(operatorRepository.findById(anyLong())).thenReturn(Optional.of(operator));
        Mockito.when(operatorRepository.save(any(Operator.class))).thenReturn(operator);

        // Testing the method
        invoiceService.assignOperatorToInvoice(1L, 2L);

        // Assertions
        assertEquals(1, operator.getInvoices().size());
        assertEquals(invoice, operator.getInvoices().iterator().next());
    }

    @Test
    void getTotalAmountInvoiceBetweenDates() {
        // Mocking the behavior of the repository
        Mockito.when(invoiceRepository.getTotalAmountInvoiceBetweenDates(any(Date.class), any(Date.class))).thenReturn(100.0f);

        // Testing the method
        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(new Date(), new Date());

        // Assertions
        assertEquals(100.0f, totalAmount);
    }
}