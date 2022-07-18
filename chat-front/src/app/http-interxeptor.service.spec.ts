import { TestBed } from '@angular/core/testing';

import { HttpInterxeptorService } from './http-interxeptor.service';

describe('HttpInterxeptorService', () => {
  let service: HttpInterxeptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterxeptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
