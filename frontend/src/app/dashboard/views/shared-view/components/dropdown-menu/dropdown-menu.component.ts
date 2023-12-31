import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { createPopper } from '@popperjs/core';



@Component({
  selector: 'app-dropdown-menu',
  templateUrl: './dropdown-menu.component.html',
  styleUrls: ['./dropdown-menu.component.css']
})
export abstract class DropdownMenuComponent implements AfterViewInit {

  public dropdownPopoverShow = false;
  @ViewChild("btnDropdownRef", { static: false }) btnDropdownRef!: ElementRef;
  @ViewChild("popoverDropdownRef", { static: false })
  public popoverDropdownRef!: ElementRef;

  public ngAfterViewInit(): void {
    createPopper(
      this.btnDropdownRef.nativeElement,
      this.popoverDropdownRef.nativeElement,
      {
        placement: "bottom-start",
      }
    );
  }

  public toggleDropdown(event: MouseEvent): void {
    event.preventDefault();
    this.dropdownPopoverShow = !this.dropdownPopoverShow;
  }

}
