/* Populate Fee table. */

INSERT
INTO FEE 
	(
		NAME, 
		DESCRIPTION, 
		FEE_VALUE
	) 
VALUES 
	(
		'Basic Tax', 
		'You know what? It will not cost you a thing!', 
		0
	);

INSERT
INTO FEE 
	(
		NAME, 
		DESCRIPTION, 
		FEE_VALUE
	) 
VALUES 
	(
		'Sales Tax', 
		'Basic Sales Taxes, it is applicable on all goods except Books, Food, Medical Products.', 
		10
	);
	
INSERT
INTO FEE 
	(
		NAME, 
		DESCRIPTION, 
		FEE_VALUE
	) 
VALUES 
	(
		'Import Tax', 
		'Sales Tax applicable on all imported goods, with no exceptions.', 
		5
	);