Feature: Prices

  Scenario: llamada 1 rest a price
    Given peticion a la API "price"
    When a las 10:00 del día 16 para el producto 35455 para el brand 1
    Then nos devuelve un 200 con datos