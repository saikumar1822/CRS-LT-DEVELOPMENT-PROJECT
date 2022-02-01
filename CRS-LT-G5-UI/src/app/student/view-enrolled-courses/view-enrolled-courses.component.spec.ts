import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewEnrolledCoursesComponent } from './view-enrolled-courses.component';

describe('ViewEnrolledCoursesComponent', () => {
  let component: ViewEnrolledCoursesComponent;
  let fixture: ComponentFixture<ViewEnrolledCoursesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewEnrolledCoursesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewEnrolledCoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
