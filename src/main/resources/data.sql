delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;

insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'FLTO', 'wheat taco', 'WRAP' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'COTO', 'corn taco', 'WRAP' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'GRBF', 'ground beef', 'MEAT' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'CARN', 'carne', 'MEAT' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'TMTO', 'tomato', 'VEGGIES' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'LETC', 'lettuce', 'VEGGIES' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'CHED', 'cheddar', 'CHEESE' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'JACK', 'monterrey jack', 'CHEESE' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'SLSA', 'hot salsa', 'SAUCE' );
insert into Ingredient (id, ingredient_name, ingredient_type) values ( 'SRCR', 'sour cream', 'SAUCE' );