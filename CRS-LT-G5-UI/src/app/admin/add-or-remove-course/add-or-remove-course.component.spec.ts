import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrRemoveCourseComponent } from './add-or-remove-course.component';

describe('AddOrRemoveCourseComponent', () => {
  let component: AddOrRemoveCourseComponent;
  let fixture: ComponentFixture<AddOrRemoveCourseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOrRemoveCourseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOrRemoveCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
