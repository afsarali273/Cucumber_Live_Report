import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class CucumberApiService{

constructor(private httpclient: HttpClient){}


getAllFeatures(): Observable<any>{
    return this.httpclient.get("http://localhost:8080/test/features");
}


}