import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveOrRejectStudentsComponent } from './approve-or-reject-students.component';

describe('ApproveOrRejectStudentsComponent', () => {
  let component: ApproveOrRejectStudentsComponent;
  let fixture: ComponentFixture<ApproveOrRejectStudentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveOrRejectStudentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveOrRejectStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
