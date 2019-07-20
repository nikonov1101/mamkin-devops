import { TestBed } from '@angular/core/testing';

import { HelloService } from './hello.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('HelloService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ]
  }));

  it('should be created', () => {
    const service: HelloService = TestBed.get(HelloService);
    expect(service).toBeTruthy();
    expect(true).toBeTruthy();
  });
});
