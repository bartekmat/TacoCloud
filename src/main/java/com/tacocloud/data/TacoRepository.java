package com.tacocloud.data;


import com.tacocloud.models.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
