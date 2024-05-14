import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleCompComponent } from './sample-comp.component';

describe('SampleCompComponent', () => {
  let component: SampleCompComponent;
  let fixture: ComponentFixture<SampleCompComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SampleCompComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SampleCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
