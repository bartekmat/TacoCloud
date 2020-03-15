package com.tacocloud.data;

import com.tacocloud.models.Ingredient;
import com.tacocloud.models.Taco;
import com.tacocloud.web.DateToTimestampConverter;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco design) {
        int tacoId = saveTacoInfo(design);
        design.setId(tacoId);
        for (Ingredient ingredient : design.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return design;
    }
    private int saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                "insert into taco (taco_name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        factory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(taco.getTaco_name(), DateToTimestampConverter.getTimestamp(taco.getCreatedAt())));


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    private  void saveIngredientToTaco(Ingredient ingredient, int tacoID){
        jdbcTemplate.update(
                "insert into Taco_Ingredients (taco, ingredient) values ( ?, ? )",
                tacoID, ingredient.getId());
    }
}
