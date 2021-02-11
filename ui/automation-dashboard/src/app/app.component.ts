import { Component } from '@angular/core';
import { from } from 'rxjs';
import {CucumberApiService} from '../app/services/cucumberapi.services';
import {FeaturesDto} from './Dto/FeaturesDto';
import * as $ from 'jquery' 
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'automation-dashboard';

  constructor(private _cucumberServices: CucumberApiService) {}

  listFeatures: FeaturesDto[] = [];
  
  ngOnInit() {

    // JQuery for row click
      $( "tbody > tr > td" ).on( "click", function() {
        alert( $( this ).text() );
});

    this._cucumberServices.getAllFeatures().toPromise().then((data) => {
      this.listFeatures = data;
      this.listFeatures.forEach(x =>{

        var featureName = x.featureUri;
        x.featureUri = featureName?.split("/").pop()?.split(".")[0].toUpperCase();
      })
    }).catch(err => console.log("Error "+err.status +" " +err.statusText));

  
  }
}
