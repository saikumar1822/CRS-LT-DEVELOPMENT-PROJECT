import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportCardGenerationComponent } from './report-card-generation.component';

describe('ReportCardGenerationComponent', () => {
  let component: ReportCardGenerationComponent;
  let fixture: ComponentFixture<ReportCardGenerationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportCardGenerationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportCardGenerationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
