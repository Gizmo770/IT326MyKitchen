export class Account {
  accountId?: number;
  name?: string;
  userName?: string;
  email?: string;
  phoneNumber?: string;
  phoneCarrier?: string;
  lowIngredientThreshold?: number;
  Fridge?: Fridge;
  ShoppingList?: ShoppingList;

  constructor(id: number, name: string, username: string,
    email: string, phoneNumber: string, phoneCarrier: string,
    lowIngredientThreshold: number) {
    this.accountId = id;
    this.name = name;
    this.userName = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.phoneCarrier = phoneCarrier;
    this.lowIngredientThreshold = lowIngredientThreshold;
  }
}

export class Fridge {
  fridgeId?: number;
  Account?: Account;
  fridgeIngredients?: Ingredient[];
}

export class ShoppingList {
  listId?: number;
  account?: Account;
  shoppingIngredients?: ShoppingListIngredient[];
}

export class Ingredient {
  ingredientId?: number;
  name?: string;
  quantity?: number;
  expirationDate?: Date;
}

export class ShoppingListIngredient {
  shoppingList?: ShoppingList;
  ingredient?: Ingredient;
  priority?: number;
}
