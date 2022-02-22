ALTER TABLE label ALTER id SET DEFAULT (UUID());

DROP PROCEDURE IF EXISTS drop_unique_cocktail;
create procedure drop_unique_cocktail() 
begin
    SET @c1 = (select count(*)
	from information_schema.table_constraints
	where constraint_schema = 'cocktails' and table_name = 'cocktail' and constraint_name = 'description_id_unique');
	IF (@c1 > 0) 
		THEN
		ALTER TABLE cocktail ALTER id SET DEFAULT (UUID()),
		DROP CONSTRAINT description_id_UNIQUE;
	END IF;
end;
call drop_unique_cocktail();

DROP PROCEDURE IF EXISTS drop_unique_ingredient;
create procedure drop_unique_ingredient() 
begin
    SET @c2 = (select count(*)
	from information_schema.table_constraints
	where constraint_schema = 'cocktails' and table_name = 'ingredient' and constraint_name = 'description_id_unique');
	IF (@c2 > 0) 
		THEN
		ALTER TABLE ingredient ALTER id SET DEFAULT (UUID()),
		DROP CONSTRAINT description_id_UNIQUE;
	END IF;
end;
call drop_unique_ingredient();

ALTER TABLE cocktail_ingredient ALTER id SET DEFAULT (UUID());