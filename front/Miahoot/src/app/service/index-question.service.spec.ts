import { TestBed } from '@angular/core/testing';

import { IndexQuestionService } from './index-question.service';

describe('IndexQuestionService', () => {
  let service: IndexQuestionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IndexQuestionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
