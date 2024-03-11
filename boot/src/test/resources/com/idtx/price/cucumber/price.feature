Feature: Prices

  Scenario Outline: llamada 1 rest a price

    Given peticion a la API "price"
    When a las <hora>:<minuto> del día <dia> para el producto <producto> para el brand <brand>
    Then nos devuelve un <httpStatus> con un precio de <precio>€

    Examples:
      | hora  | minuto | dia | producto | brand | httpStatus |  precio |
      | 10    | 00     | 14  | 35455    | 1     | 200        |  35.50  |
      | 16    | 00     | 14  | 35455    | 1     | 200        |  25.45  |
      | 21    | 00     | 14  | 35455    | 1     | 200        |  35.50  |
      | 10    | 00     | 15  | 35455    | 1     | 200        |  30.50  |
      | 21    | 00     | 16  | 35455    | 1     | 200        |  38.95  |
