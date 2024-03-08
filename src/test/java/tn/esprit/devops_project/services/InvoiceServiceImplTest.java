package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InvoiceServiceImplTest {

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    OperatorRepository operatorRepository;


    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    InvoiceServiceImpl invoiceService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void retrieveAllInvoices() {
        // Arrange
        List<Invoice> expectedInvoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        // Act
        List<Invoice> actualInvoices = invoiceService.retrieveAllInvoices();

        // Assert
        assertEquals(expectedInvoices.size(), actualInvoices.size());
        assertEquals(expectedInvoices, actualInvoices);
    }

    @Test
    void cancelInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(invoiceId);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.cancelInvoice(invoiceId);

        // Assert
        assertTrue(invoice.getArchived());
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void retrieveInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice expectedInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // Act
        Invoice actualInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Assert
        assertNotNull(actualInvoice);
        assertEquals(expectedInvoice, actualInvoice);
    }

//    @Test
//    void testGetInvoicesBySupplier() {
//        Long idSupplier = 1L;
//        Supplier supplier = new Supplier();
//        Set<Invoice> invoices = new HashSet<>();
//        invoices.add(new Invoice());
//        supplier.setInvoices(invoices);
//        when(supplierRepository.findById(idSupplier)).thenReturn(java.util.Optional.of(supplier));
//
//        List<Invoice> result = invoiceService.getInvoicesBySupplier(idSupplier);
//
//        assertEquals(invoices.size(), result.size());
//    }

//    @Test
//    void assignOperatorToInvoice() {
//        // Arrange
//        Long operatorId = 1L;
//        Long invoiceId = 1L;
//        Operator operator = new Operator();
//        operator.setIdOperateur(operatorId);
//        Invoice invoice = new Invoice();
//        invoice.setIdInvoice(invoiceId);
//       // operator.initializeInvoices(); // Initializing operator's invoices if null
//        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));
//        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
//
//        // Act
//        invoiceService.assignOperatorToInvoice(operatorId, invoiceId);
//
//        // Assert
//        assertTrue(operator.getInvoices().contains(invoice));
//        verify(operatorRepository, times(1)).save(operator);
//    }

    @Test
    void getTotalAmountInvoiceBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedTotalAmount = 1000.0f;
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);

        // Act
        float actualTotalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assert
        assertEquals(expectedTotalAmount, actualTotalAmount);
    }
}