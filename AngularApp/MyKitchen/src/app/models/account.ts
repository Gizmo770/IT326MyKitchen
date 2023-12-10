export class Account {
    id?: number;
    name?: string;
    username?: string;
    Fridge?: Fridge;
    ShoppingList?: ShoppingList;
}

export class Fridge {
    id?: number;
    ingredients?: Ingredient[];
}

export class ShoppingList {
    id?: number;
    ingredients?: Ingredient[];
}

export class Ingredient {
    id?: number;
    name?: string;
    quantity?: number;
    expirationDate?: Date;
}