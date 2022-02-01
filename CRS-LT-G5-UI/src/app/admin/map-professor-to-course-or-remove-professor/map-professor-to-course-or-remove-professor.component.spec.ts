import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapProfessorToCourseOrRemoveProfessorComponent } from './map-professor-to-course-or-remove-professor.component';

describe('MapProfessorToCourseOrRemoveProfessorComponent', () => {
  let component: MapProfessorToCourseOrRemoveProfessorComponent;
  let fixture: ComponentFixture<MapProfessorToCourseOrRemoveProfessorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MapProfessorToCourseOrRemoveProfessorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MapProfessorToCourseOrRemoveProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
