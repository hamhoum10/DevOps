import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { StockService } from './stock.service';

describe('StockService', () => {
  let service: StockService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [StockService]
    });
    service = TestBed.inject(StockService);
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

    const req = httpMock.expectOne('http://localhost:8082/stock');
    expect(req.request.method).toBe('GET');
    req.flush(dummyData);
  });

  it('should fetch data by ID', () => {
    const dummyId = '123';
    const dummyData = { /* Dummy data here */ };

    service.fetchData(dummyId).subscribe(data => {
      expect(data).toEqual(dummyData);
    });

    const req = httpMock.expectOne(`http://localhost:8082/stock/${dummyId}`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyData);
  });

  it('should fetch quantity by ID', () => {
    const dummyId = '456';
    const dummyData = { /* Dummy data here */ };

    service.fetchQuantity(dummyId).subscribe(data => {
      expect(data).toEqual(dummyData);
    });

    const req = httpMock.expectOne(`http://localhost:8082/stock/quantity/${dummyId}`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyData);
  });

  it('should add stock', () => {
    const dummyStock = { /* Dummy stock data here */ };
    const dummyResponse = { /* Dummy response data here */ };

    service.addStock(dummyStock).subscribe(response => {
      expect(response).toEqual(dummyResponse);
    });

    const req = httpMock.expectOne('http://localhost:8082/stock');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(dummyStock);
    req.flush(dummyResponse);
  });
});
