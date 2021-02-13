import { Component } from '@angular/core';
import { from } from 'rxjs';
import {CucumberApiService} from '../app/services/cucumberapi.services';
import {FeaturesDto} from './Dto/FeaturesDto';
import * as $ from 'jquery' ;
import * as Highcharts from 'highcharts';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'automation-dashboard';
  highcharts = Highcharts;

  constructor(private _cucumberServices: CucumberApiService) {}

  listFeatures: FeaturesDto[] = [];
  
  chartOptions: {} | undefined;
  
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

  this.chartOptions = {   
      chart : {
         plotBorderWidth: null,
         plotShadow: false
      },
      title : {
         text: 'Browser market shares at a specific website, 2014'   
      },
      tooltip : {
         pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions : {
         pie: {
            allowPointSelect: true,
            cursor: 'pointer',
      
            dataLabels: {
               enabled: false           
            },
      
            showInLegend: true
         }
      },
      series : [{
         type: 'pie',
         name: 'Browser share',
         data: [
            ['Firefox',   45.0],
            ['IE',       26.8],
            {
               name: 'Chrome',
               y: 12.8,
               sliced: true,
               selected: true
            },
            ['Safari',    8.5],
            ['Opera',     6.2],
            ['Others',      0.7]
         ]
      }]
   };

  }

   
}
