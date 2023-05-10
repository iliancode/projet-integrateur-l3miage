import { TestBed } from '@angular/core/testing';

import { SelectedMiahootService } from './selected-miahoot.service';

describe('SelectedMiahootService', () => {
  let service: SelectedMiahootService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SelectedMiahootService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
