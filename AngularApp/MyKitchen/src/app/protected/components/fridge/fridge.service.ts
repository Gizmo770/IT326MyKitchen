import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

interface Fridge {
    id: number;
    name: string;
    ingredients: string[];
}

@Injectable({
    providedIn: 'root',
})
export class FridgeService {
    private fridges: Fridge[] = [
        { id: 1, name: 'Kitchen Fridge', ingredients: ['Eggs', 'Milk', 'Vegetables'] },
        { id: 2, name: 'Outdoor Fridge', ingredients: ['Water', 'Fruits', 'Soda'] },
    ];

    private fridgesSubject: BehaviorSubject<Fridge[]> = new BehaviorSubject<Fridge[]>(this.fridges);

    getFridges(): Observable<Fridge[]> {
        return this.fridgesSubject.asObservable();
    }

    createFridge(newFridge: Fridge): void {
        this.fridges.push(newFridge);
        this.updateFridgesSubject();
    }

    updateFridge(updatedFridge: Fridge): void {
        const index = this.fridges.findIndex((f) => f.id === updatedFridge.id);
        if (index !== -1) {
            this.fridges[index] = updatedFridge;
            this.updateFridgesSubject();
        }
    }

    deleteFridge(fridgeId: number): void {
        this.fridges = this.fridges.filter((f) => f.id !== fridgeId);
        this.updateFridgesSubject();
    }

    private updateFridgesSubject(): void {
        this.fridgesSubject.next([...this.fridges]);
    }
}
