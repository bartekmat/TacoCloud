package com.tacocloud.data;

import com.tacocloud.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, ingredient_name, ingredient_type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbcTemplate.queryForObject("select id, ingredient_name, ingredient_type from Ingredient where id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("insert into Ingredient (id, ingredient_name, ingredient_type ) values (?, ?, ?)",
                ingredient.getId(),ingredient.getIngredient_name(),ingredient.getIngredient_type().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException{
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("ingredient_name"),
                Ingredient.Type.valueOf(resultSet.getString("ingredient_type")));
    }
}
