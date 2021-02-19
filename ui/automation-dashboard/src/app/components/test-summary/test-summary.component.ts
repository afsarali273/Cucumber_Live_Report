import { Component, OnInit } from '@angular/core';
import { FeaturesDto } from '../../Dto/FeaturesDto';
import { TestSummaryService } from '../../services/test-summary.service';

@Component({
  selector: 'app-test-summary',
  templateUrl: './test-summary.component.html',
  styleUrls: ['./test-summary.component.css'],
})
export class TestSummaryComponent implements OnInit {
  constructor(private testSummaryService: TestSummaryService) {}

  listFeatures: FeaturesDto[] = [];

  ngOnInit(): void {
    this.testSummaryService
      .getAllFeatures()
      .toPromise()
      .then((data) => {
        this.listFeatures = data;
        this.listFeatures.forEach((x) => {
          var featureName = x.featureUri;
          x.featureUri = featureName
            ?.split('/')
            .pop()
            ?.split('.')[0]
            .toUpperCase();
        });
      })
      .catch((err) =>
        console.log('Error ' + err.status + ' ' + err.statusText)
      );
  }
}
