export interface IProduct {
  id?: number;
  name?: string;
  description?: string;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
  ) {}
}
