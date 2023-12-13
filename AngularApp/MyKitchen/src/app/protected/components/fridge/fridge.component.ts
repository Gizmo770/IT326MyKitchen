import { Component, OnInit } from '@angular/core';
import { FridgeService } from './fridge.service';

interface Fridge {
  id: number;
  name: string;
  ingredients: string[];
}

@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.scss'],
})
export class FridgeComponent implements OnInit {
  fridges: Fridge[] = [];
  newFridgeName: string = '';
  updatedFridgeName: string = '';
  updatedIngredients: string = '';

  constructor(private fridgeService: FridgeService) { }

  ngOnInit() {
    this.loadFridges();
  }

  private loadFridges() {
    this.fridgeService.getFridges().subscribe((fridges: Fridge[]) => {
      this.fridges = fridges;
    });
  }

  createFridge() {
    const newFridge: Fridge = {
      id: this.fridges.length + 1,
      name: this.newFridgeName,
      ingredients: [],
    };
    this.fridgeService.createFridge(newFridge);
    this.newFridgeName = ''; // Clear the input field
  }

  updateFridge(fridge: Fridge) {
    const updatedFridge: Fridge = {
      ...fridge,
      name: this.updatedFridgeName || fridge.name,
      ingredients: this.updatedIngredients
        ? this.updatedIngredients.split(',').map((i) => i.trim())
        : fridge.ingredients,
    };
    this.fridgeService.updateFridge(updatedFridge);
    this.updatedFridgeName = ''; // Clear the input field
    this.updatedIngredients = '';
  }

  deleteFridge(fridge: Fridge) {
    this.fridgeService.deleteFridge(fridge.id);
  }
}
