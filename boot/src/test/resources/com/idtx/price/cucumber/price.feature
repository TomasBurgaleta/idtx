Feature: Prices

  Scenario Outline: llamada 1 rest a price

    Given peticion a la API "price"
    When a las <hora>:<minuto> del día <dia> para el producto <producto> para el brand <brand>
    Then nos devuelve un <httpStatus>

    Examples:
      | hora  | minuto | dia | producto | brand | httpStatus |
      | 10    | 00     | 14  | 35455    | 1     | 200        |
      | 16    | 00     | 14  | 35455    | 1     | 200        |
      | 21    | 00     | 14  | 35455    | 1     | 200        |
      | 10    | 00     | 15  | 35455    | 1     | 200        |
      | 21    | 00     | 16  | 35455    | 1     | 200        |
