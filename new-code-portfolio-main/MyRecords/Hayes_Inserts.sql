INSERT INTO Agent VALUES (626,'Derek','Wood Work Ent');
INSERT INTO Agent VALUES (524,'Susan','Total Estates');
INSERT INTO Agent VALUES (424,'Dahlia','Real Estate Go');
INSERT INTO Agent VALUES (679,'Xavier','Flip n Go');
INSERT INTO Agent VALUES (504,'Paul','Prime listing');
COMMIT;

INSERT INTO Seller VALUES (1492,'Barry','Cheryl',149-0000);
INSERT INTO Seller VALUES (1738,'Tina','Moe',888-1234);
INSERT INTO Seller VALUES (1812,'Tasha','Kane',222-2222);
INSERT INTO Seller VALUES (2001,'Blake','Brittany',111-3333);
INSERT INTO Seller VALUES (2020,'Lee','Tracy',300-6000);
COMMIT;

INSERT INTO Buyer VALUES (999,'John','678-5555',10,70,626);
INSERT INTO Buyer VALUES (909,'Carol','773-1234',25,80,626);
INSERT INTO Buyer VALUES (090,'Karen','989-0000',15,90,424);
INSERT INTO Buyer VALUES (969,'Ophelia','000-1234',75,100,679);
INSERT INTO Buyer VALUES (900,'Tom','900-2323',60,100,504);
COMMIT;

INSERT INTO PhoneNumber VALUES (626,555-7777);
INSERT INTO PhoneNumber VALUES (524,777-3333);
INSERT INTO PhoneNumber VALUES (424,234-1234);
INSERT INTO PhoneNumber VALUES (679,679-1234);
INSERT INTO PhoneNumber VALUES (504,504-0404);
COMMIT;

INSERT INTO House VALUES (100,2037,'Hollywood Drive',4500,626,1492);
INSERT INTO House VALUES (101,1667,'Windy Creek',5000,524,1738);
INSERT INTO House VALUES (102,32,'Dolce',10000,424,1812);
INSERT INTO House VALUES (103,41,'Winthrop',8500,679,2001);
INSERT INTO House VALUES (104,40,'Somerville',6500,504,2020);
COMMIT;

INSERT INTO Posting VALUES (626,100,35,90);
INSERT INTO Posting VALUES (524,101,35,65);
INSERT INTO Posting VALUES (424,102,25,100);
INSERT INTO Posting VALUES (679,103,25,70);
INSERT INTO Posting VALUES (504,104,20,100);
COMMIT;

--This query is the logic of the between query
Select Address,StreetName from Buyer,House Where HouseAgentId = BuyerAgentId and MinPrice Between 10 and 20;