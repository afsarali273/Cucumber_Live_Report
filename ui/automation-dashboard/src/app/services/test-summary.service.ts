import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FeaturesDto } from '../Dto/FeaturesDto';

@Injectable({
  providedIn: 'root',
})
export class TestSummaryService {
  constructor(private httpClient: HttpClient) {}

  getAllFeatures(): Observable<FeaturesDto[]> {
    return this.httpClient.get<FeaturesDto[]>(
      'http://localhost:8080/test/features',
      {
        responseType: 'json',
      }
    );
  }
}
