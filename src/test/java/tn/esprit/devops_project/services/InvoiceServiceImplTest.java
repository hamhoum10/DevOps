package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        // Add some sample invoices to the list
        invoices.add(new Invoice());
        invoices.add(new Invoice());
        when(invoiceRepository.findAll()).thenReturn(invoices);
        List<Invoice> result = invoiceService.retrieveAllInvoices();
        assertEquals(2, result.size());
    }
    @Test
    void cancelInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setArchived(false); // Assuming invoice is not yet archived
        when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.ofNullable(invoice));

        invoiceService.cancelInvoice(invoiceId);

        assertEquals(true, invoice.getArchived()); // Use the appropriate method or attribute
        verify(invoiceRepository, times(1)).save(invoice); // Verify that save method is called once
    }


    @Test
    void retrieveInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.ofNullable(invoice));

        Invoice result = invoiceService.retrieveInvoice(invoiceId);

        assertEquals(invoice, result); // Verify that the retrieved invoice is the same as the expected one
    }

    // Add tests for other methods similarly
}
