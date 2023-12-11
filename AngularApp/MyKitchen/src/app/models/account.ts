export class Account {
  id?: number;
  name?: string;
  username?: string;
  email?: string;
  phoneNumber?: string;
  phoneCarrier?: string;
  lowIngredientThreshold?: number;
  Fridge?: Fridge;
  ShoppingList?: ShoppingList;

  constructor(id: number, name: string, username: string,
    email: string, phoneNumber: string, phoneCarrier: string,
    lowIngredientThreshold: number) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.phoneCarrier = phoneCarrier;
    this.lowIngredientThreshold = lowIngredientThreshold;
  }
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
