/* Populate Category table. */
	
INSERT
INTO CATEGORY 
	(
		NAME, 
		DESCRIPTION, 
		FEE
	) 
SELECT 
	'Poiuyt Category', 
	'The category all Poiuyts belong to. The mother of all Poiuyts. You know the rest. Books, Food and Medical Products must belong to this Category only!', 
	ID 
FROM FEE 
WHERE NAME = 'Basic Tax';
	
INSERT
INTO CATEGORY 
	(
		NAME, 
		DESCRIPTION, 
		FEE
	) 	
SELECT 
	'Sold Good', 
	'The best goodies that your beautiful Market can offer come with a price. And a tax too. Sorry for that, we didn''t mean to.', 
	ID 
FROM FEE 
WHERE NAME = 'Sales Tax';
	
INSERT
INTO CATEGORY 
	(
		NAME, 
		DESCRIPTION, 
		FEE
	) 
SELECT 
	'Imported Good', 
	'Yes, imported goods come with an additional tax. We didn''t want it. You didn''t want it. What else can we say?', 
	ID 
FROM FEE 
WHERE NAME = 'Import Tax';