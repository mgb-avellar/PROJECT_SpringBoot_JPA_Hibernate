package com.example.demo.entities.enums;

public enum OrderStatus {

    /*
    Este ENUM é um pouco diferente do que já fizemos: estamos atribuindo manualmente
    os códigos de cada tipo enumerado.
     */

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    /*
    Porque este ENUM é diferente, precisamos do código abaixo:
    - definimos uma variável de código tipo private;
    - um construtor tipo private (isso é novidade no curso);
    - um método estático para percorrer pelos códigos possíveis e retornar o que é necessário
     */

    private int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code.");
    }
}
