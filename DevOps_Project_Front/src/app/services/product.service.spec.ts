import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProductService } from './product.service';

describe('ProductService', () => {
  let service: ProductService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProductService]
    });
    service = TestBed.inject(ProductService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch all data', () => {
    const dummyData = [{ /* Dummy data here */ }];

    service.fetchAllData().subscribe(data => {
      expect(data).toEqual(dummyData);
    });

    const req = httpMock.expectOne('http://localhost:8082/product');
    expect(req.request.method).toBe('GET');
    req.flush(dummyData);
  });

  it('should fetch data by ID', () => {
    const dummyId = '123';
    const dummyData = { /* Dummy data here */ };

    service.fetchData(dummyId).subscribe(data => {
      expect(data).toEqual(dummyData);
    });

    const req = httpMock.expectOne(`http://localhost:8082/product/stock/${dummyId}`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyData);
  });

  it('should fetch data by category', () => {
    const dummyCategory = 'electronics';
    const dummyData = [{ /* Dummy data here */ }];

    service.fetchDataByCategory(dummyCategory).subscribe(data => {
      expect(data).toEqual(dummyData);
    });

    const req = httpMock.expectOne(`http://localhost:8082/productCategoy/${dummyCategory}`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyData);
  });

  it('should add product', () => {
    const dummyProduct = { /* Dummy product data here */ };
    const dummyIdStock = '456';
    const dummyResponse = { /* Dummy response data here */ };

    service.addProduct(dummyProduct, dummyIdStock).subscribe(response => {
      expect(response).toEqual(dummyResponse);
    });

    const req = httpMock.expectOne(`http://localhost:8082/product/${dummyIdStock}`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(dummyProduct);
    req.flush(dummyResponse);
  });

  it('should delete data by ID', () => {
    const dummyId = '789';

    service.deleteData(dummyId).subscribe(() => {
      // Test delete success
    });

    const req = httpMock.expectOne(`http://localhost:8082/product/${dummyId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });
});