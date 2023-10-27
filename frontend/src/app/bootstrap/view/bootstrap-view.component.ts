import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bootstrap',
  templateUrl: './bootstrap-view.component.html',
  styleUrls: ['./bootstrap-view.component.css']
})
export class BootstrapViewComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.router.navigateByUrl("/dashboard")
    }, 500);
  }

}
