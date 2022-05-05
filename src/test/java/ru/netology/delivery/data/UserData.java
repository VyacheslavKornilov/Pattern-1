package ru.netology.delivery.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserData {
    private  String city;
    private  String name;
    private  String phone;
}
